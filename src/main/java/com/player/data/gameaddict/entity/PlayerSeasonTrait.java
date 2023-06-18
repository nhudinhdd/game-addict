package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.PlayerSeasonTraitRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "playerseason_trait")
@Data
@NoArgsConstructor
public class PlayerSeasonTrait extends BaseEntity {

    @Id
    @Column(name = "ps_trait_id")
    private String psTraitID = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(
            name = "trait_id",
            referencedColumnName = "trait_id"
    )
    private Trait trait;

    @ManyToOne
    @JoinColumn(
            name = "player_season_id",
            referencedColumnName = "player_season_id"
    )
    private PlayerSeason playerSeason;

    @Override
    public String getId() {
        return psTraitID;
    }

    public PlayerSeasonTrait(PlayerSeason playerSeason, Trait trait) {
        this.playerSeason =playerSeason;
        this.trait = trait;
    }
}
