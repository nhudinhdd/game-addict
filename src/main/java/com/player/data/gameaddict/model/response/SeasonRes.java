package com.player.data.gameaddict.model.response;

import com.player.data.gameaddict.entity.Season;
import lombok.Data;

@Data
public class SeasonRes {

    private String seasonID;
    private String shortName;
    private String fullName;
    private String logo;

    public SeasonRes(Season season) {
        this.seasonID = season.getSeasonID();
        this.shortName = season.getShortName();
        this.fullName = season.getFullName();
        this.logo = season.getLogo();
    }
}
