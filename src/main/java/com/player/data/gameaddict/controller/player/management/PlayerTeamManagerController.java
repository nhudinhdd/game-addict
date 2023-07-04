package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.PlayerTeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/management/player-team")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlayerTeamManagerController {

    private final PlayerTeamService playerTeamService;

    @PostMapping
    public ResponseEntity<MetaDataRes<?>> insertPlayerTeam(@RequestBody PlayerTeamRequest playerTeamRequest) {
        MetaDataRes<?> metaDataRes = playerTeamService.insertPlayerTeam(playerTeamRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping("{player-team-id}")
    public ResponseEntity<MetaDataRes<?>> updatePlayerTeam(@RequestBody PlayerTeamRequest playerTeamRequest,
                                                           @PathVariable("player-team-id") String playerTeamID) {
        MetaDataRes<?> metaDataRes = playerTeamService.updatePlayerTeam(playerTeamRequest, playerTeamID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
