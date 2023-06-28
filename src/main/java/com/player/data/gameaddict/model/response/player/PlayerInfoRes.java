package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.util.DateUtil;
import lombok.Data;

@Data
public class PlayerInfoRes {

    private String playerID;
    private NationRes nationRes;
    private String firstName;
    private String lastName;
    private String birthDay;

    public PlayerInfoRes(PlayerInfo playerInfo) {
        this.playerID = playerInfo.getPlayerID();
        this.nationRes = new NationRes(playerInfo.getNation());
        this.firstName = playerInfo.getFirstName();
        this.lastName = playerInfo.getLastName();
        this.birthDay = DateUtil.localDateToString(playerInfo.getBirthday());
    }
}
