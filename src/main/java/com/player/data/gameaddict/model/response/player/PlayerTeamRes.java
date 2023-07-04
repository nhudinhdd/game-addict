package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.PlayerTeam;
import lombok.Data;

@Data
public class PlayerTeamRes {

    private String playerTeamID;
    private PlayerInfoRes playerInfoRes;
    private TeamRes teamRes;
    private String startYear;
    private String endYear;

    public PlayerTeamRes(PlayerTeam playerTeam) {
        this.playerTeamID = playerTeam.getPlayerTeamID();
        this.playerInfoRes = new PlayerInfoRes(playerTeam.getPlayerInfo());
        this.teamRes = new TeamRes(playerTeam.getTeam());
        this.startYear = playerTeam.getStartYear();
        this.endYear = playerTeam.getEndYear();
    }
}
