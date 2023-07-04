package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.TournamentRes;
import com.player.data.gameaddict.service.player.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tour")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<TournamentRes>>> getTournament() {
        MetaDataRes<List<TournamentRes>> metaDataRes = tournamentService.getTournaments();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
    @GetMapping(value = "{tournament-id}")
    public ResponseEntity<MetaDataRes<TournamentRes>> getTournamentDetail(@PathVariable("tournament-id") String tournamentID) {
        MetaDataRes<TournamentRes> metaDataRes = tournamentService.getTournamentDetail(tournamentID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
