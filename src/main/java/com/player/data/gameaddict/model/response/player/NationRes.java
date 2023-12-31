package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.Nation;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class NationRes {

    private String nationID;
    private String nationName;
    private String ensign;
    private String altEnsign;
    private String titleEnsign;
    private String captionEnsign;
    private String continentName;
    private String continentID;

    public NationRes(Nation nation) {
        if(!Objects.isNull(nation)) {
            this.nationID = nation.getNationID();
            this.nationName = nation.getNationName();
            this.ensign = nation.getEnsign();
            this.altEnsign = nation.getAltEnsign();
            this.titleEnsign = nation.getTitleEnsign();
            this.captionEnsign = nation.getCaptionEnsign();
            if(!Objects.isNull(nation.getContinent())){
                this.continentName = nation.getContinent().getContinentName();
                this.continentID = nation.getContinent().getContinentID();
            }
        }
    }
}
