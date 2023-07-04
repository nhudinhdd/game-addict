package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.Season;
import lombok.Data;

@Data
public class SeasonRes {

    private String seasonID;
    private String shortName;
    private String fullName;
    private String logo;
    private String bigLogo;
    private String altLogoSeason;
    private String titleLogoSeason;
    private String captionLogoSeason;
    private String backgroundLogo;
    private String altBackgroundLogo;
    private String titleBackgroundLogo;
    private String captionBackgroundLogo;

    public SeasonRes(Season season) {
        this.seasonID = season.getSeasonID();
        this.shortName = season.getShortName();
        this.fullName = season.getFullName();
        this.logo = season.getLogo();
        this.bigLogo = season.getBigLogo();
        this.altLogoSeason = season.getAltLogoSeason();
        this.titleLogoSeason = season.getTitleLogoSeason();
        this.captionLogoSeason = season.getCaptionLogoSeason();
        this.backgroundLogo =season.getBackgroundLogo();
        this.altBackgroundLogo = season.getAltBackgroundLogo();
        this.titleBackgroundLogo = season.getTitleBackgroundLogo();
        this.captionBackgroundLogo = season.getCaptionBackgroundLogo();
    }
}
