package com.player.data.gameaddict.model.response;

import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.util.DateUtil;
import lombok.Data;

@Data
public class PlayerInfoRes {

    private String playerID;
    private NationRes nationRes;
    private String shortName;
    private String fullName;
    private String birthDay;

    public PlayerInfoRes(PlayerInfo playerInfo) {
        this.playerID = playerInfo.getPlayerID();
        this.nationRes = new NationRes(playerInfo.getNation());
        this.shortName = playerInfo.getShortName();
        this.fullName = playerInfo.getFullName();
        this.birthDay = DateUtil.localDateToString(playerInfo.getBirthday());
    }
}
