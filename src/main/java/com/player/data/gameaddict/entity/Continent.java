package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.player.ContinentRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Continent extends BaseEntity{

    @Id
    @Column(name = "continent_id")
    private String continentID = UUID.randomUUID().toString();
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

    @Override
    public String getId() {
        return null;
    }
    public Continent(ContinentRequest continentRequest, boolean isNew) {
        this.continentName = continentRequest.getContinentName();
        setNew(isNew);
    }

    public Continent(ContinentRequest continentRequest, String continentID) {
        this.continentName = continentRequest.getContinentName();
        this.continentID = continentID;
    }

    public Continent(String continentID) {
        this.continentID = continentID;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "continentID='" + continentID + '\'' +
                ", continentName='" + continentName + '\'' +
                ", nations=" + nations +
                '}';
    }
}
