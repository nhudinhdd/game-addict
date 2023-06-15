package com.player.data.gameaddict.controller.admin;

import com.player.data.gameaddict.model.request.TournamentRequest;
import com.player.data.gameaddict.model.response.MetaDataRes;
import com.player.data.gameaddict.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management/api")
public class TournamentAdminController {

    private final TournamentService tournamentService;

    public TournamentAdminController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> insertTournament(@RequestBody TournamentRequest tournamentRequest) {
        MetaDataRes<?> metaDataRes = tournamentService.insertTournament(tournamentRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "{tour-id}")
    public ResponseEntity<?> updateTournament(@PathVariable("tour-id") String tourID, @RequestBody TournamentRequest tournamentRequest) {
        MetaDataRes<?> metaDataRes = tournamentService.updateTournament(tourID, tournamentRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
