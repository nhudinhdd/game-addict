package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.player.NationRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Nation extends BaseEntity {
    @Id
    @Column(name = "nation_id")
    private String nationID = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(
            name = "continent_id",
            referencedColumnName = "continent_id"
    )
    private Continent continent;
    @Column(name = "nation_name")
    private String nationName;
    @Column(name = "ensign")
    private String ensign;
    @Column(name = "alt_ensign")
    private String altEnsign;
    @Column(name = "title_ensign")
    private String titleEnsign;
    @Column(name = "caption_ensign")
    private String captionEnsign;
    @OneToMany(
            mappedBy = "nation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE
            }
    )
    private List<PlayerInfo> playerInfos;

    @Override
    public String getId() {
        return nationID;
    }

    public Nation(String nationID, String nationName, String ensign, String altEnsign, String titleEnsign, String captionEnsign) {
        this.nationID = nationID;
        this.nationName = nationName;
        this.ensign = ensign;
        this.altEnsign = altEnsign;
        this.titleEnsign = titleEnsign;
        this.captionEnsign =captionEnsign;
    }
    public Nation(NationRequest nationRequest, Continent continent, boolean isNew) {
        this.nationName = nationRequest.getNationName();
        this.ensign = nationRequest.getEnsign();
        this.continent = continent;
        setNew(isNew);
    }

    public Nation(NationRequest nationRequest, String continentID, String nationID) {
        this.nationName = nationRequest.getNationName();
        this.ensign = nationRequest.getEnsign();
        this.continent = new Continent(continentID);
        this.nationID = nationID;
    }
}
