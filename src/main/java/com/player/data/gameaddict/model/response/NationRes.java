package com.player.data.gameaddict.model.response;

import com.player.data.gameaddict.entity.Nation;
import lombok.Data;

@Data
public class NationRes {

    private String nationID;
    private String nationName;
    private String ensign;

    public NationRes(Nation nation) {
        this.nationID = nation.getNationID();
        this.nationName = nation.getNationName();
        this.ensign = nation.getEnsign();
    }
}
