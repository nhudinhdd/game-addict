package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.request.player.TeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.player.TeamRepository;
import com.player.data.gameaddict.repository.player.TournamentRepository;
import com.player.data.gameaddict.service.AwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
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
        if(tournamentOptional.isEmpty()) {
            log.warn("Invalid tourID={}", teamRequest.getTourID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        teamRequest.setTeamLogo(awsS3Service.upload(teamRequest.getTeamLogoFile()));
        Team team = new Team(teamRequest, tournamentOptional.get(), true);
        log.info("Start insert team={}", team);
        teamRepository.save(team);
        log.info("Finish insert team with teamID={}", team.getTeamID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateTeam(TeamRequest teamRequest, String teamID) throws IOException {
        Optional<Team> teamOptional = teamRepository.findById(teamID);
        if(teamOptional.isEmpty())  {
            log.warn("Invalid teamID={}", teamID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        teamRequest.setTeamLogo(awsS3Service.upload(teamRequest.getTeamLogoFile()));
        Team team = new Team(teamRequest, teamID, teamOptional.get().getTournament());
        log.info("Start update team={}", team);
        teamRepository.save(team);
        log.info("Finish update team with teamID={}", teamID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}