package com.player.data.gameaddict.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Nation extends BaseEntity {

    @Id
    @Column(name = "nation_id")
    private String nationID;
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
    @OneToMany(
            mappedBy = "nation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE
            }
    )
    private List<PlayerInfo> playerInfos;
}
