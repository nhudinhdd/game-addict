package com.player.data.gameaddict.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Continent extends BaseEntity{

    @Id
    @Column(name = "continent_id")
    private String continentID;
    @Column(name = "continent_name")
    private String continentName;
    @OneToMany(
            mappedBy = "continent",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST, // object has been update/delete in database,
                    CascadeType.REMOVE //object hase been remove from database
            }
    )
    private List<Nation> nations;
}
