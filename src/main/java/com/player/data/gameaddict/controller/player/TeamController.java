package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TournamentService tournamentService;

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<Team>>> getTraits(@RequestParam(name = "tour-id") String tourID) {
        MetaDataRes<List<Team>> metaDataRes = tournamentService.getTeamByTournamentID(tourID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

}
