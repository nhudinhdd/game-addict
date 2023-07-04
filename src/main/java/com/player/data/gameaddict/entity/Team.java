package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.player.TeamRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Team extends BaseEntity {
    @Id
    @Column(name = "team_id")
    private String teamID = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(
            name = "tour_id",
            referencedColumnName = "tour_id"
    )
    private Tournament tournament;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "team_logo")
    private String teamLogo;
    @OneToMany(
            mappedBy = "team",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<PlayerTeam> playerTeams;

    @Column(name = "alt_team_logo")
    private String altTeamLogo;
    @Column(name = "title_team_logo")
    private String titleTeamLogo;
    @Column(name = "caption_team_logo")
    private String captionTeamLogo;

    public Team(TeamRequest teamRequest, Tournament tournament, boolean isNew) {
        this.teamName = teamRequest.getTeamName();
        this.teamLogo = teamRequest.getTeamLogo();
        this.tournament = tournament;
        this.altTeamLogo = teamRequest.getAltLogo();
        this.titleTeamLogo = teamRequest.getTitleLogo();
        this.captionTeamLogo = teamRequest.getCaptionLogo();
        setNew(isNew);
    }

    public Team() {

    }

    public Team(TeamRequest teamRequest, String teamID, Tournament tournament) {
        this.teamName = teamRequest.getTeamName();
        this.teamLogo = teamRequest.getTeamLogo();
        this.altTeamLogo = teamRequest.getAltLogo();
        this.titleTeamLogo =teamRequest.getTitleLogo();
        this.captionTeamLogo = teamRequest.getCaptionLogo();
        this.teamID = teamID;
        this.tournament = tournament;
    }

    @Override
    public String getId() {
        return teamID;
    }
}
