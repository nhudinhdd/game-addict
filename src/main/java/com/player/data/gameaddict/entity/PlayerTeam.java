package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.PlayerTeamRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "player_team")
@Data
@NoArgsConstructor
public class PlayerTeam extends BaseEntity {

    @Id
    @Column(name = "player_team_id")
    private String playerTeamID = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(
            name = "player_id",
            referencedColumnName = "player_id"
    )
    private PlayerInfo playerInfo;
    @ManyToOne
    @JoinColumn(
            name = "team_id",
            referencedColumnName = "team_id"
    )
    private Team team;
    @Column(name = "start_year")
    private String startYear;
    @Column(name = "end_year")
    private String endYear;

    @Override
    public String getId() {
        return playerTeamID;
    }

    public PlayerTeam(PlayerTeamRequest request, PlayerInfo playerInfo, Team team) {
        this.playerInfo = playerInfo;
        this.team = team;
        this.startYear =request.getStarYear();
        this.endYear = request.getEndYear();
    }
}
