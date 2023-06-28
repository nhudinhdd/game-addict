package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.player.TraitRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Trait extends BaseEntity {
    @Id
    @Column(name = "trait_id")
    private String traitID = UUID.randomUUID().toString();
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logo;
    @Column(name = "alt_logo_trait")
    private String altLogoTrait;
    @Column(name = "title_logo_trait")
    private String titleLogoTrait;
    @Column(name = "caption_logo_trait")
    private String captionLogoTrait;

    @OneToMany(
            mappedBy = "trait",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<PlayerSeasonTrait> playerSeasonTrait;

    public Trait(TraitRequest traitRequest, boolean isNew) {
        this.name = traitRequest.getName();
        this.description = traitRequest.getDescription();
        this.logo = traitRequest.getFileNameTraitLogo();
        setNew(isNew);
    }

    public Trait(TraitRequest traitRequest, String traitID) {
        this.traitID = traitID;
        this.name = traitRequest.getName();
        this.description = traitRequest.getDescription();
        this.logo = traitRequest.getFileNameTraitLogo();
    }

    @Override
    public String getId() {
        return traitID;
    }
}
