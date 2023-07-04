package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.Trait;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitResponse {
    private String traitID;
    private String description;
    private String logo;
    private String altLogo;
    private  String titleLogo;
    private String captionLog;

    public TraitResponse(Trait trait) {
        this.traitID = trait.getTraitID();
        this.description = trait.getDescription();
        this.logo = trait.getLogo();
        this.altLogo = trait.getAltLogoTrait();
        this.titleLogo = trait.getTitleLogoTrait();
        this.captionLog = trait.getCaptionLogoTrait();
    }
}
