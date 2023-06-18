package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tour")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<Tournament>>> getTournament() {
        MetaDataRes<List<Tournament>> metaDataRes = tournamentService.getTournaments();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
