package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.TournamentRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Tournament extends BaseEntity {

    @Id
    @Column(name = "tour_id")
    private String tourID  = UUID.randomUUID().toString();
    @Column(name = "tour_name")
    private String tourName;

    @OneToMany (
            mappedBy = "tournament",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST, // object has been update/delete in database,
                    CascadeType.REMOVE //object hase been remove from database
            }
    )
    private List<Team> teams =  new ArrayList<>();
    public Tournament(TournamentRequest request, boolean isNew) {
        this.tourName = request.getTourName();
        setNew(isNew);
    }

    public Tournament(TournamentRequest request, String tourID) {
        this.tourName = request.getTourName();
        this.tourID = tourID;
    }

    @Override
    public String getId() {
        return tourID;
    }
}
