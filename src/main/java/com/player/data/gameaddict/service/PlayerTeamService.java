package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.entity.PlayerTeam;
import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.model.request.PlayerTeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.PlayerInfoRepository;
import com.player.data.gameaddict.repository.PlayerTeamRepository;
import com.player.data.gameaddict.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if(playerInfoOptional.isEmpty()) {
            log.info("Invalid playerID = {}", request.getPlayerID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_INFO_ID_INVALID);
        }

        Optional<Team> teamOptional = teamRepository.findById(request.getTeamId());
        if(teamOptional.isEmpty()) {
            log.info("Invalid teamID = {}", request.getTeamId());
            return new MetaDataRes<>(MetaDataEnum.TEAM_ID_INVALID);
        }

        PlayerTeam playerTeam = new PlayerTeam(request, playerInfoOptional.get(), teamOptional.get());
        playerTeamRepository.save(playerTeam);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
