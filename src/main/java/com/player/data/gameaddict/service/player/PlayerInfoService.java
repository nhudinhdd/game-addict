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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        Optional<Nation> nationOptional =  nationRepository.findById(request.getNationID());
        if(nationOptional.isEmpty()) {
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
        Optional<PlayerInfo> playerInfoOptional =  playerInfoRepository.findById(playerID);
        if(playerInfoOptional.isEmpty()){
            log.warn("Invalid playerID = {}", playerID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        Optional<Nation> nationOptional =  nationRepository.findById(request.getNationID());
        if(nationOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        PlayerInfo playerInfo = new PlayerInfo(request, nationOptional.get(), playerID);
        log.info("Start update playerInfo = {}", playerInfo);
        playerInfoRepository.save(playerInfo);
        log.info("Finish update playerInfo with playerInfoID = {}", playerInfo.getPlayerID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataList<List<PlayerInfoRes>> getPlayerInfos(String lastName, int page, int pageSize) {
        log.info("Start get list player info by lastName = {}, page={}, pageSize={}", lastName, page, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PlayerInfo> playerInfoPage = playerInfoRepository.findAllByLastNameContainingIgnoreCase(lastName,pageable);
        List<PlayerInfo> playerInfoList = playerInfoPage.getContent();
        int totalPage = playerInfoPage.getTotalPages();
        List<PlayerInfoRes> playerInfoRes = playerInfoList.stream().map(PlayerInfoRes::new).toList();
        log.info("Finish get list player info with size={}", playerInfoRes.size());
        return new MetaDataList<>(MetaDataEnum.SUCCESS, playerInfoRes, totalPage);
    }
}