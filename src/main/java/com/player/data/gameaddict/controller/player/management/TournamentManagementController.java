package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.TournamentRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/management/tour")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TournamentManagementController {

    private final TournamentService tournamentService;

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
