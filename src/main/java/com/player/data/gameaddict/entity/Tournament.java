package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.TournamentRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Tournament(TournamentRequest request) {
        this.tourName = request.getTourName();
    }

    public Tournament(TournamentRequest request, String tourID) {
        this.tourName = request.getTourName();
        this.tourID = tourID;
    }
}
