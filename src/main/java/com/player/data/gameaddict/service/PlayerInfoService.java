package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Nation;
import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.model.request.PlayerInfoRequest;
import com.player.data.gameaddict.model.response.PlayerInfoRes;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.NationRepository;
import com.player.data.gameaddict.repository.PlayerInfoRepository;
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
        if(nationOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        PlayerInfo playerInfo = new PlayerInfo(request, nationOptional.get(), true);
        playerInfoRepository.save(playerInfo);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updatePlayerInfo(PlayerInfoRequest request, String playerID) {
        Optional<PlayerInfo> playerInfoOptional =  playerInfoRepository.findById(playerID);
        if(playerInfoOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        Optional<Nation> nationOptional =  nationRepository.findById(request.getNationID());
        if(nationOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        PlayerInfo playerInfo = new PlayerInfo(request, nationOptional.get(), playerID);
        playerInfoRepository.save(playerInfo);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataList<List<PlayerInfoRes>> getPlayerInfos(String shortName, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<PlayerInfo> playerInfoPage = playerInfoRepository.findAllByShortNameContainingIgnoreCase(shortName,pageable);
        List<PlayerInfo> playerInfoList = playerInfoPage.getContent();
        int totalPage = playerInfoPage.getTotalPages();
        List<PlayerInfoRes> playerInfoRes = playerInfoList.stream().map(PlayerInfoRes::new).toList();
        return new MetaDataList<>(MetaDataEnum.SUCCESS, playerInfoRes, totalPage);
    }
}
