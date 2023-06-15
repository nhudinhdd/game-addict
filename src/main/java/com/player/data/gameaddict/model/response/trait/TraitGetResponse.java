package com.player.data.gameaddict.model.response.trait;

import com.player.data.gameaddict.entity.Trait;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitGetResponse {
    private String traitID;
    private String description;
    private String logo;

    public TraitGetResponse(Trait trait, String domain) {
        this.traitID = trait.getTraitID();
        this.description = trait.getDescription();
        this.logo = domain.concat(trait.getLogo());
    }
}
