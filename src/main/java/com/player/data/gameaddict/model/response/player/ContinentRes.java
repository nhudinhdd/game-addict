package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.Continent;
import lombok.Data;

@Data
public class ContinentRes {

    private String continentID;
    private String continentName;

    public ContinentRes(Continent continent) {
        this.continentID = continent.getContinentID();
        this.continentName = continent.getContinentName();
    }
}
