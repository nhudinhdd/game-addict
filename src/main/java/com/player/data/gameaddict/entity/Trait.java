package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.TraitRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Trait extends BaseEntity{
    @Id
    @Column(name = "trait_id")
    private String traitID = UUID.randomUUID().toString();
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logo;

    public Trait(TraitRequest traitRequest) {
        this.name = traitRequest.getName();
        this.description = traitRequest.getDescription();
        this.logo = traitRequest.getFileNameTraitLogo();
    }

    public Trait(TraitRequest traitRequest, String traitID) {
        this.traitID = traitID;
        this.name = traitRequest.getName();
        this.description = traitRequest.getDescription();
        this.logo = traitRequest.getFileNameTraitLogo();
    }
}
