package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tour")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<Tournament>>> getTournament() {
        MetaDataRes<List<Tournament>> metaDataRes = tournamentService.getTournaments();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
