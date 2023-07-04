package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.entity.PlayerTeam;
import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.model.request.player.PlayerTeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.PlayerTeamRes;
import com.player.data.gameaddict.repository.player.PlayerInfoRepository;
import com.player.data.gameaddict.repository.player.PlayerTeamRepository;
import com.player.data.gameaddict.repository.player.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayerTeamService {

    private final PlayerTeamRepository playerTeamRepository;
    private final PlayerInfoRepository playerInfoRepository;
    private final TeamRepository teamRepository;

    public PlayerTeamService(PlayerTeamRepository playerTeamRepository, PlayerInfoRepository playerInfoRepository, TeamRepository teamRepository) {
        this.playerTeamRepository = playerTeamRepository;
        this.playerInfoRepository = playerInfoRepository;
        this.teamRepository = teamRepository;
    }

    public MetaDataRes<?> insertPlayerTeam(PlayerTeamRequest request) {
        Optional<PlayerInfo> playerInfoOptional = playerInfoRepository.findById(request.getPlayerID());
        if (playerInfoOptional.isEmpty()) {
            log.info("Invalid playerID = {}", request.getPlayerID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_INFO_ID_INVALID);
        }

        Optional<Team> teamOptional = teamRepository.findById(request.getTeamID());
        if (teamOptional.isEmpty()) {
            log.info("Invalid teamID = {}", request.getTeamID());
            return new MetaDataRes<>(MetaDataEnum.TEAM_ID_INVALID);
        }

        PlayerTeam playerTeam = new PlayerTeam(request, playerInfoOptional.get(), teamOptional.get());
        playerTeamRepository.save(playerTeam);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updatePlayerTeam(PlayerTeamRequest request, String playerTeamID) {
        Optional<PlayerTeam> playerTeamOptional = playerTeamRepository.findById(playerTeamID);
        if (playerTeamOptional.isEmpty()) {
            log.info("Invalid playerID = {}", request.getPlayerID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_TEAM_ID_INVALID);
        }

        Optional<PlayerInfo> playerInfoOptional = playerInfoRepository.findById(request.getPlayerID());
        if (playerInfoOptional.isEmpty()) {
            log.info("Invalid playerID = {}", request.getPlayerID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_INFO_ID_INVALID);
        }

        Optional<Team> teamOptional = teamRepository.findById(request.getTeamID());
        if (teamOptional.isEmpty()) {
            log.info("Invalid teamID = {}", request.getTeamID());
            return new MetaDataRes<>(MetaDataEnum.TEAM_ID_INVALID);
        }

        PlayerTeam playerTeam = new PlayerTeam(request, playerInfoOptional.get(), teamOptional.get(), playerTeamID);
        playerTeamRepository.save(playerTeam);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataList<List<PlayerTeamRes>> getPlayerTeams(String playerID, String teamID, int page, int pageSize) {
        log.info("Start get list player team by playerID = {}, teamID={}, page={}, pageSize={}", playerID, teamID, page, pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PlayerTeam> playerTeamPage = playerTeamRepository.findByPlayerIdAndTeamID(playerID, teamID, pageable);
        List<PlayerTeamRes> playerTeamResList = new ArrayList<>();
        int totalPage = 0;
        if (!CollectionUtils.isEmpty(playerTeamPage.getContent())) {
            playerTeamResList = playerTeamPage.getContent().stream().map(PlayerTeamRes::new).collect(Collectors.toList());
            totalPage = playerTeamPage.getTotalPages();
        }
        log.info("Finish list player team with size={}", playerTeamResList.size());
        return new MetaDataList<>(MetaDataEnum.SUCCESS, playerTeamResList, totalPage);
    }

    public MetaDataRes<PlayerTeamRes> getPlayerTeamDetail(String playerTeamID) {
        log.info("Start get getPlayerTeamDetail playerTeamID = {}", playerTeamID);
        Optional<PlayerTeam> playerTeamOptional = playerTeamRepository.findById(playerTeamID);
        if(playerTeamOptional.isEmpty()) return new  MetaDataRes<>(MetaDataEnum.PLAYER_TEAM_ID_INVALID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new PlayerTeamRes(playerTeamOptional.get()));
    }
}
