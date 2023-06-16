package com.player.data.gameaddict.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Season extends BaseEntity {

    @Id
    @Column(name = "season_id")
    private String sessionID;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "full_name")
    private String logo;
}
