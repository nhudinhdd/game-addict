package com.player.data.gameaddict.service.player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.data.gameaddict.common.constant.Common;
import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.*;
import com.player.data.gameaddict.model.common.PlayerName;
import com.player.data.gameaddict.model.request.player.PlayerSeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.PlayerSeasonDetailRes;
import com.player.data.gameaddict.model.response.player.PlayerSeasonRes;
import com.player.data.gameaddict.repository.player.*;
import com.player.data.gameaddict.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerSeasonService {

    private final PlayerSeasonRepository playerSeasonRepository;
    private final SeasonRepository seasonRepository;
    private final PlayerInfoRepository playerInfoRepository;
    private final TraitRepository traitRepository;
    private final PlayerSeasonTraitRepository playerSeasonTraitRepository;
    private final AwsS3Service awsS3Service;
    @Transactional(rollbackFor = Exception.class)
    public MetaDataRes<?> insertPlayerSeason(PlayerSeasonRequest request, MultipartFile playerAvatar) throws Exception {
        Optional<Season> seasonOptional = seasonRepository.findById(request.getSeasonID());
        if(seasonOptional.isEmpty()) {
            log.warn("Invalid seasonID = {}", request.getSeasonID());
            return new MetaDataRes<>(MetaDataEnum.SEASON_ID_INVALID);
        }
        Optional<PlayerInfo> playerInfoOptional = playerInfoRepository.findById(request.getPlayerID());
        if(playerInfoOptional.isEmpty()) {
            log.warn("Invalid playerID = {}", request.getSeasonID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_INFO_ID_INVALID);
        }

        Optional<PlayerSeason> playerSeasonOptional = playerSeasonRepository.getPlayerSeasonByPlayerInfo_PlayerIDAndSeason_SeasonID(request.getPlayerID(), request.getSeasonID());
        if(playerSeasonOptional.isPresent())
            return new MetaDataRes<>(MetaDataEnum.PLAYER_SEASON_ALREADY_EXISTS);
        request.setAvatar(awsS3Service.upload(playerAvatar));
        PlayerSeason playerSeason = new PlayerSeason(request, playerInfoOptional.get(), seasonOptional.get(), true);
        log.info("Start insert playerSeason = {}",playerSeason);
        playerSeasonRepository.save(playerSeason);
        ObjectMapper mapper = new ObjectMapper();

        List<String> traitIDsRequest = (mapper.readValue(request.getTraitIDs(), new TypeReference<>() {
        }));
        updatePlayerSeasonTrait(traitIDsRequest,playerSeason.getPlayerSeasonID(), playerSeason);
        log.info("Finish insert player season with playerSeasonID = {}", playerSeason.getPlayerSeasonID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    @Transactional(rollbackFor = Exception.class)
    public MetaDataRes<?> updatePlayerSeason(PlayerSeasonRequest request, String playerSeasonID, MultipartFile playerAvatar) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Optional<PlayerSeason> playerSeasonOptional = playerSeasonRepository.findById(playerSeasonID);
        if(playerSeasonOptional.isEmpty()) {
            log.warn("Invalid player seasonID = {}", request.getSeasonID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_SEASON_ID_INVALID);
        }
        Optional<Season> seasonOptional = seasonRepository.findById(request.getSeasonID());
        if(seasonOptional.isEmpty()) {
            log.warn("Invalid seasonID = {}", request.getSeasonID());
            return new MetaDataRes<>(MetaDataEnum.SEASON_ID_INVALID);
        }
        Optional<PlayerInfo> playerInfoOptional = playerInfoRepository.findById(request.getPlayerID());
        if(playerInfoOptional.isEmpty()) {
            log.warn("Invalid playerID = {}", request.getSeasonID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_INFO_ID_INVALID);
        }
        List<String> traitIDsRequest = (mapper.readValue(request.getTraitIDs(), new TypeReference<>() {
        }));

        String avatarFile = (Objects.isNull(playerAvatar))? playerSeasonOptional.get().getAvatar(): awsS3Service.upload(playerAvatar);
        request.setAvatar(avatarFile);
        PlayerSeason playerSeason = new PlayerSeason(request, playerInfoOptional.get(), seasonOptional.get(), playerSeasonID);
        log.info("Start update playerSeason = {}",playerSeason);
        playerSeasonRepository.save(playerSeason);
        log.info("Finish update player season with playerSeasonID = {}", playerSeason.getPlayerSeasonID());
        updatePlayerSeasonTrait(traitIDsRequest,playerSeasonID, playerSeason);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<PlayerSeasonDetailRes> getPlayerSeasonDetail(String id) {
        log.info("Start get player season detail with playerSeasonID={}", id);
        Optional<PlayerSeason> playerSeasonOptional = playerSeasonRepository.findById(id);
        if(playerSeasonOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.PLAYER_SEASON_ID_INVALID);
        PlayerSeason playerSeason = playerSeasonOptional.get();
        List<PlayerSeason> playerSeasonRelated =
                playerSeasonRepository.getPlayerSeasonByPlayerInfo_PlayerIDAndPlayerSeasonIDIsNot(playerSeason.getPlayerInfo().getPlayerID(),
                        playerSeason.getPlayerSeasonID());
        List<PlayerSeasonRes> playerSeasonRelatedRes = playerSeasonRelated.stream().map(PlayerSeasonRes::new).toList();
        PlayerSeasonDetailRes res = new PlayerSeasonDetailRes(playerSeason, playerSeasonRelatedRes);
        log.info("Finish get player season detail with playerSeasonID={}", id);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, res);
    }

    public MetaDataList<List<PlayerSeasonRes>> getPlayerSeasons(String playerName, String seasonID, String teamID,
                                                               String traitID, int page, int pageSize) {
        log.info("Start get player season by playerName={}, seasonID={}, teamID={}, traitID={}, pageSize={}, page={}",
                playerName, seasonID, teamID, traitID, pageSize, page);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PlayerSeasonBaseInfo> playerSeasons =
                playerSeasonRepository.getPlayerSeasons(playerName,seasonID, pageable);
        int totalPage = playerSeasons.getTotalPages();
        List<PlayerSeasonRes> playerSeasonResList = playerSeasons.getContent().stream().map(PlayerSeasonRes::new).toList();
        log.info("Finish get player season with size={}", playerSeasonResList.size());
        return new MetaDataList<>(MetaDataEnum.SUCCESS, playerSeasonResList, totalPage);
    }

    public MetaDataRes<List<String>> getPopularPlayer(){
        log.info("Start get popular player");
        List<PlayerName> playerPopularName = Common.NAME_PLAYER_POPULAR;
        List<String> playerSeasonID = new ArrayList<>();
        for (PlayerName name: playerPopularName) {
           playerSeasonID.addAll(playerSeasonRepository.getPopularPlayer(name.getFirstName(), name.getLastName()));
        }
        log.info("Finish get player season popular with size={}", playerSeasonID.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, playerSeasonID);
    }
    private void updatePlayerSeasonTrait(List<String> traitIDsRequest, String playerSeasonID, PlayerSeason playerSeason) throws Exception {
        if(!CollectionUtils.isEmpty(traitIDsRequest)) {
            List<Trait> traits = traitRepository.findAllById(traitIDsRequest);
            if(CollectionUtils.isEmpty(traits))  throw new Exception("Trait ID invalid");
            // delete player season in playerSeasonTrait
            playerSeasonTraitRepository.deleteByPlayerSeason_PlayerSeasonID(playerSeasonID);
            List<PlayerSeasonTrait> playerSeasonTraits = traits.stream().map(trait -> new PlayerSeasonTrait(playerSeason, trait)).toList();
            playerSeasonTraitRepository.saveAll(playerSeasonTraits);
        }
    }

}
