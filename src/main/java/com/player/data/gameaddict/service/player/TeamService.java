package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Continent;
import com.player.data.gameaddict.entity.Nation;
import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.request.player.TeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.NationRes;
import com.player.data.gameaddict.model.response.player.TeamRes;
import com.player.data.gameaddict.repository.player.TeamRepository;
import com.player.data.gameaddict.repository.player.TournamentRepository;
import com.player.data.gameaddict.service.AwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public MetaDataRes<?> insertTeam(TeamRequest teamRequest, MultipartFile file) throws IOException {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(teamRequest.getTourID());
        if(tournamentOptional.isEmpty()) {
            log.warn("Invalid tourID={}", teamRequest.getTourID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        teamRequest.setTeamLogo(awsS3Service.upload(file));
        Team team = new Team(teamRequest, tournamentOptional.get(), true);
        log.info("Start insert team={}", team);
        teamRepository.save(team);
        log.info("Finish insert team with teamID={}", team.getTeamID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateTeam(TeamRequest teamRequest, String teamID, MultipartFile file) throws IOException {
        Optional<Team> teamOptional = teamRepository.findById(teamID);
        if(teamOptional.isEmpty())  {
            log.warn("Invalid teamID={}", teamID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        String fileURL = Objects.isNull(file) ? teamOptional.get().getTeamLogo() : awsS3Service.upload(file);
        teamRequest.setTeamLogo(fileURL);
        Team team = new Team(teamRequest, teamID, teamOptional.get().getTournament());
        log.info("Start update team={}", team);
        teamRepository.save(team);
        log.info("Finish update team with teamID={}", teamID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<List<TeamRes>> getListTeams(String tourID) {
        log.info("Start get list team by tourID = {}", tourID);
        List<TeamRes> teamResList = new ArrayList<>();
        if (StringUtils.isBlank(tourID)) {
            List<Team> teams = teamRepository.findAll();
            if (!CollectionUtils.isEmpty(teams)) teamResList = teams.stream().map(TeamRes::new).toList();
        } else {
            Optional<Tournament> tournamentOptional = tournamentRepository.findById(tourID);
            if (tournamentOptional.isPresent()) {
                teamResList = tournamentOptional.get().getTeams().stream().map(TeamRes::new).toList();
            }
        }

        log.info("Finish get list nation with size = {}", teamResList.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, teamResList);
    }

    public MetaDataRes<TeamRes> getTeamDetail(String teamID) {
        log.info("Start get team detail by teamID = {}", teamID);
        Optional<Team> teamOptional = teamRepository.findById(teamID);
        if (teamOptional.isEmpty()) {
            log.info("Invalid teamID = {}", teamID);
            return new MetaDataRes<>(MetaDataEnum.TEAM_ID_INVALID);
        }
        TeamRes teamRes = new TeamRes(teamOptional.get());
        log.info("Finish get team detail by team = {}", teamID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, teamRes);
    }
}
