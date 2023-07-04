package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.Team;
import lombok.Data;

@Data
public class TeamRes {

    private String teamID;
    private TournamentRes tournamentRes;
    private String teamName;
    private String teamLogo;
    private String altLogo;
    private String titleLogo;
    private String captionLogo;

    public TeamRes(Team team) {
        this.teamID = team.getTeamID();
        this.tournamentRes = new TournamentRes(team.getTournament());
        this.teamName = team.getTeamName();
        this.teamLogo = team.getTeamLogo();
        this.altLogo = team.getAltTeamLogo();
        this.titleLogo = team.getTitleTeamLogo();
        this.captionLogo = team.getCaptionTeamLogo();
    }
}
