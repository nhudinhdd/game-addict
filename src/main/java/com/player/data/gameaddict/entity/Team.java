package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.TeamRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Team extends BaseEntity{
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

    public Team(TeamRequest teamRequest, Tournament tournament) {
        this.teamName = teamRequest.getTeamName();
        this.teamLogo = teamRequest.getTeamLogo();
        this.tournament = tournament;
    }

    public Team() {

    }

    public Team(TeamRequest teamRequest, String teamID, Tournament tournament) {
        this.teamName = teamRequest.getTeamName();
        this.teamLogo = teamRequest.getTeamLogo();
        this.teamID = teamID;
        this.tournament = tournament;
    }
}
