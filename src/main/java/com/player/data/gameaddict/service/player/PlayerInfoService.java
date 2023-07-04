package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Nation;
import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.model.request.player.PlayerInfoRequest;
import com.player.data.gameaddict.model.response.player.PlayerInfoRes;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.player.NationRepository;
import com.player.data.gameaddict.repository.player.PlayerInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerInfoService {

    private final PlayerInfoRepository playerInfoRepository;
    private final NationRepository nationRepository;

    public PlayerInfoService(PlayerInfoRepository playerInfoRepository, NationRepository nationRepository) {
        this.playerInfoRepository = playerInfoRepository;
        this.nationRepository = nationRepository;
    }

    public MetaDataRes<?> insertPlayerInfo(PlayerInfoRequest request) {
        Optional<Nation> nationOptional = nationRepository.findById(request.getNationID());
        if (nationOptional.isEmpty()) {
            log.warn("Invalid nationID = {}", request.getNationID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        PlayerInfo playerInfo = new PlayerInfo(request, nationOptional.get(), true);
        log.info("Start insert playerInfo = {}", playerInfo);
        playerInfoRepository.save(playerInfo);
        log.info("Finish insert playerInfo with playerInfoID = {}", playerInfo.getPlayerID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updatePlayerInfo(PlayerInfoRequest request, String playerID) {
        Optional<PlayerInfo> playerInfoOptional = playerInfoRepository.findById(playerID);
        if (playerInfoOptional.isEmpty()) {
            log.warn("Invalid playerID = {}", playerID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        Optional<Nation> nationOptional = nationRepository.findById(request.getNationID());
        if (nationOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        PlayerInfo playerInfo = new PlayerInfo(request, nationOptional.get(), playerID);
        log.info("Start update playerInfo = {}", playerInfo);
        playerInfoRepository.save(playerInfo);
        log.info("Finish update playerInfo with playerInfoID = {}", playerInfo.getPlayerID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataList<List<PlayerInfoRes>> getPlayerInfos(String lastName, int page, int pageSize, String nationID) {
        log.info("Start get list player info by lastName = {}, page={}, pageSize={}", lastName, page, pageSize);
        List<PlayerInfoRes> playerInfoRes;
        List<PlayerInfo> playerInfoList;
        int totalPage = 1;
        if (page < 0) {
            playerInfoList = playerInfoRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<PlayerInfo> playerInfoPage;
            if (StringUtils.isBlank(nationID)) {
                playerInfoPage = playerInfoRepository.findAllByLastNameContainingIgnoreCase(lastName, pageable);
            } else {
                playerInfoPage = playerInfoRepository.findAllByLastNameContainingIgnoreCaseAndNationId(lastName, pageable, nationID);
            }
            playerInfoList = playerInfoPage.getContent();
            totalPage = playerInfoPage.getTotalPages();
        }
        playerInfoRes = playerInfoList.stream().map(PlayerInfoRes::new).toList();
        log.info("Finish get list player info with size={}", playerInfoRes.size());
        return new MetaDataList<>(MetaDataEnum.SUCCESS, playerInfoRes, totalPage);
    }

    public MetaDataRes<PlayerInfoRes> getPlayerInfoDetail(String playerInfoID) {
        log.info("Start get getPlayerInfoDetail playerInfoID = {}", playerInfoID);
        Optional<PlayerInfo> playerInfoOptional = playerInfoRepository.findById(playerInfoID);
        if (playerInfoOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.PLAYER_INFO_ID_INVALID);

        log.info("Finish get getPlayerInfoDetail playerInfoID={}", playerInfoID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new PlayerInfoRes(playerInfoOptional.get()));
    }
}
