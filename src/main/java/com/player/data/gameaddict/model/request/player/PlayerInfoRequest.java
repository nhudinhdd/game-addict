package com.player.data.gameaddict.model.request.player;

import lombok.Data;

import java.util.List;

@Data
public class PlayerInfoRequest {

    private String nationID;
    private String firstName;
    private String lastName;
    private String birthday;
    private String playerStory;
    private String teamID;
    private List<PlayerTeamHistoryRequest> historyRequests;
}
