package com.player.data.gameaddict.model.response.player;

import com.player.data.gameaddict.entity.Tournament;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TournamentRes {

    private String tourID;
    private String tourName;

    public TournamentRes(Tournament tournament) {
        this.tourID = tournament.getTourID();
        this.tourName = tournament.getTourName();
    }
}
