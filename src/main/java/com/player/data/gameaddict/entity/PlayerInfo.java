package com.player.data.gameaddict.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "player_info")
@Data
public class PlayerInfo extends  BaseEntity{

    @Id
    @Column(name = "player_id")
    private String playerID;

    @ManyToOne
    @JoinColumn(
            name = "nation_id",
            referencedColumnName = "nation_id"
    )
    private Nation nation;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birthday")
    private String birthday;
}
