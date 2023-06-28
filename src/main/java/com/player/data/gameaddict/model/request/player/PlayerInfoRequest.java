package com.player.data.gameaddict.model.request.player;

import lombok.Data;

@Data
public class PlayerInfoRequest {

    private String nationID;
    private String firstName;
    private String lastName;
    private String birthday;
}
