package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.model.response.MetaDataRes;
import com.player.data.gameaddict.model.response.trait.TraitGetResponse;
import com.player.data.gameaddict.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {

    private final TournamentService tournamentService;

    @Autowired
    public TeamController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<Team>>> getTraits(@RequestParam(name = "tour-id") String tourID) {
        MetaDataRes<List<Team>> metaDataRes = tournamentService.getTeamByTournamentID(tourID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

}
