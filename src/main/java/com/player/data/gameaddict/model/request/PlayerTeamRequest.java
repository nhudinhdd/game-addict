package com.player.data.gameaddict.model.request;

import lombok.Data;

@Data
public class PlayerTeamRequest {

    private String playerID;
    private String teamId;
    private String starYear;
    private String endYear;
}
