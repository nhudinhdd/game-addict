package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.TeamRes;
import com.player.data.gameaddict.service.player.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/team")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeamController {

    private final TeamService teamService;

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<TeamRes>>> getListTeam(@RequestParam(name = "tour-id", required = false) String tourID) {
        MetaDataRes<List<TeamRes>> metaDataRes = teamService.getListTeams(tourID);

        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @GetMapping(value = "{team-id}")
    public ResponseEntity<MetaDataRes<TeamRes>> getTeamDetail(@PathVariable(name = "team-id") String teamID) {
        MetaDataRes<TeamRes> metaDataRes = teamService.getTeamDetail(teamID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

}
