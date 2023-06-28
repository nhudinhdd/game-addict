package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.player.SeasonRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Season extends BaseEntity {

    @Id
    @Column(name = "season_id")
    private String seasonID = UUID.randomUUID().toString();
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "logo")
    private String logo;
    @Column(name = "alt_logo_season")
    private String altLogoSeason;
    @Column(name = "title_logo_season")
    private String titleLogoSeason;
    @Column(name = "caption_logo_season")
    private String captionLogoSeason;

    @OneToMany(
            mappedBy = "season",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<PlayerSeason> playerSeasons;

    public Season(SeasonRequest seasonRequest, boolean isNew) {
        this.shortName = seasonRequest.getShortName();
        this.fullName = seasonRequest.getFullName();
        this.logo = seasonRequest.getLogo();
        setNew(isNew);
    }

    public Season(String seasonID, String shortName, String fullName, String logo, String altLogoSeason, String titleLogoSeason, String captionLogoSeason) {
        this.seasonID = seasonID;
        this.shortName = shortName;
        this.fullName = fullName;
        this.altLogoSeason = altLogoSeason;
        this.titleLogoSeason = titleLogoSeason;
        this.captionLogoSeason = captionLogoSeason;
        this.logo = logo;
    }

    public Season(SeasonRequest seasonRequest, String seasonID) {
        this.seasonID = seasonID;
        this.shortName = seasonRequest.getShortName();
        this.fullName = seasonRequest.getFullName();
        this.logo = seasonRequest.getLogo();
    }

    public Season() {

    }

    @Override
    public String getId() {
        return seasonID;
    }
}
