package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.*;
import com.player.data.gameaddict.model.request.PlayerSeasonRequest;
import com.player.data.gameaddict.model.response.PlayerSeasonDetailRes;
import com.player.data.gameaddict.model.response.PlayerSeasonRes;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.PlayerInfoRepository;
import com.player.data.gameaddict.repository.PlayerSeasonRepository;
import com.player.data.gameaddict.repository.SeasonRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerSeasonService {

    private final PlayerSeasonRepository playerSeasonRepository;
    private final SeasonRepository seasonRepository;
    private final PlayerInfoRepository playerInfoRepository;
    private final AwsS3Service awsS3Service;
    public PlayerSeasonService(PlayerSeasonRepository playerSeasonRepository, SeasonRepository seasonRepository,
                               PlayerInfoRepository playerInfoRepository, AwsS3Service awsS3Service) {
        this.playerSeasonRepository = playerSeasonRepository;
        this.seasonRepository = seasonRepository;
        this.playerInfoRepository = playerInfoRepository;
        this.awsS3Service =  awsS3Service;
    }

    public MetaDataRes<?> insertPlayerSeason(PlayerSeasonRequest request) throws IOException {
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
        request.setAvatar(awsS3Service.upload(request.getAvatarFile()));
        PlayerSeason playerSeason = new PlayerSeason(request, playerInfoOptional.get(), seasonOptional.get(), true);
        log.info("Start insert playerSeason = {}",playerSeason);
        playerSeasonRepository.save(playerSeason);
        log.info("Finish insert player season with playerSeasonID = {}", playerSeason.getPlayerSeasonID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<PlayerSeasonDetailRes> getPlayerSeasonDetail(String id) {
        log.info("Start get player season detail with playerSeasonID={}", id);
        PlayerSeasonDetail playerSeasonDetail = playerSeasonRepository.getPlayerSeasonById(id);
        PlayerSeasonDetailRes res = new PlayerSeasonDetailRes(playerSeasonDetail);
        log.info("Finish get player season detail with playerSeasonID={}", id);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, res);
    }

    public MetaDataList<List<PlayerSeasonRes>> getPlayerSeasons(String playerName, String seasonID, String teamID,
                                                               String traitID, int page, int pageSize) {
        log.info("Start get player season by playerName={}, seasonID={}, teamID={}, traitID={}, pageSize={}, page={}",
                playerName, seasonID, teamID, traitID, pageSize, page);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PlayerSeasonBaseInfo> playerSeasons =
                playerSeasonRepository.getPlayerSeasons(playerName,seasonID,teamID,traitID, pageable);
        int totalPage = playerSeasons.getTotalPages();
        List<PlayerSeasonRes> playerSeasonResList = playerSeasons.getContent().stream().map(PlayerSeasonRes::new).toList();
        log.info("Finish get player season with size={}", playerSeasonResList.size());
        return new MetaDataList<>(MetaDataEnum.SUCCESS, playerSeasonResList, totalPage);
    }

}
