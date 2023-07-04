package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.PlayerSeasonTrait;
import lombok.Data;

@Data
public class PlayerSeasonTraitRes {
    private String psTraitID;
    private TraitResponse trait;
    private PlayerSeasonRes playerSeason;

    public PlayerSeasonTraitRes(PlayerSeasonTrait playerSeasonTrait){
        this.psTraitID = playerSeasonTrait.getPsTraitID();
        this.trait = new TraitResponse(playerSeasonTrait.getTrait());
        this.playerSeason = null;
    }
}
