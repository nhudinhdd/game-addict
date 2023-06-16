package com.player.data.gameaddict.service;

import com.google.common.base.Strings;
import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.request.TeamRequest;
import com.player.data.gameaddict.model.response.MetaDataRes;
import com.player.data.gameaddict.repository.TeamRepository;
import com.player.data.gameaddict.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;
    private final AwsS3Service awsS3Service;
    @Autowired
    public TeamService(TeamRepository teamRepository, TournamentRepository tournamentRepository, AwsS3Service awsS3Service) {
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.awsS3Service = awsS3Service;
    }

    public MetaDataRes<?> insertTeam(TeamRequest teamRequest) throws IOException {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(teamRequest.getTourID());
        if(tournamentOptional.isEmpty())  return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        teamRequest.setTeamLogo(awsS3Service.upload(teamRequest.getTeamLogoFile()));
        Team team = new Team(teamRequest, tournamentOptional.get());
        teamRepository.save(team);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateTeam(TeamRequest teamRequest, String teamID) throws IOException {
        Optional<Team> teamOptional = teamRepository.findById(teamID);
        if(teamOptional.isEmpty())  return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        teamRequest.setTeamLogo(awsS3Service.upload(teamRequest.getTeamLogoFile()));
        Team team = new Team(teamRequest, teamID, teamOptional.get().getTournament());
        teamRepository.save(team);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
