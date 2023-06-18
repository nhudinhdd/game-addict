package com.player.data.gameaddict.controller.management;

import com.player.data.gameaddict.model.request.PlayerTeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.PlayerTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/player-team")
public class PlayerTeamManagerController {

    private final PlayerTeamService playerTeamService;

    @Autowired
    public PlayerTeamManagerController(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @PostMapping
    public ResponseEntity<MetaDataRes<?>> insertPlayerTeam(@RequestBody PlayerTeamRequest playerTeamRequest) {
        MetaDataRes<?> metaDataRes = playerTeamService.insertPlayerTeam(playerTeamRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
