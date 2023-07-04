package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.PlayerTeamRes;
import com.player.data.gameaddict.service.player.PlayerTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player-team")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlayerTeamController {

    private final PlayerTeamService service;

    @GetMapping("")
    public ResponseEntity<MetaDataList<List<PlayerTeamRes>>> getListPlayerTeam(@RequestParam(value = "player-id", required = false) String playerID,
                                                                               @RequestParam(value = "team-id", required = false) String teamID,
                                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                                               @RequestParam(value = "page-size", defaultValue = "10") int pageSize) {
        MetaDataList<List<PlayerTeamRes>> seaListMetaDataRes = service.getPlayerTeams(playerID, teamID, page, pageSize);
        return new ResponseEntity<>(seaListMetaDataRes, HttpStatus.OK);
    }

    @GetMapping("{player-team-id}")
    public ResponseEntity<MetaDataRes<PlayerTeamRes>> getPlayerTeamDetail(@PathVariable("player-team-id") String playerTeamID) {
        MetaDataRes<PlayerTeamRes> metaDataRes = service.getPlayerTeamDetail(playerTeamID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
