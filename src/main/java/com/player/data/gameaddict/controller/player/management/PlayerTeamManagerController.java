package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.PlayerTeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/management/player-team")
@RequiredArgsConstructor
public class PlayerTeamManagerController {

    private final PlayerTeamService playerTeamService;

    @PostMapping
    public ResponseEntity<MetaDataRes<?>> insertPlayerTeam(@RequestBody PlayerTeamRequest playerTeamRequest) {
        MetaDataRes<?> metaDataRes = playerTeamService.insertPlayerTeam(playerTeamRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
