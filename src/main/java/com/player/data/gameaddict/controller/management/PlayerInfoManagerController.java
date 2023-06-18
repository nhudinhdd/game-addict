package com.player.data.gameaddict.controller.management;

import com.player.data.gameaddict.model.request.PlayerInfoRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.PlayerInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management/api/player-info")
public class PlayerInfoManagerController {

    private final PlayerInfoService playerInfoService;

    public PlayerInfoManagerController(PlayerInfoService playerInfoService) {
        this.playerInfoService = playerInfoService;
    }

    @PostMapping
    public ResponseEntity<MetaDataRes<?>> insertPlayerInfo(@RequestBody PlayerInfoRequest request) {
        MetaDataRes<?> metaDataRes = playerInfoService.insertPlayerInfo(request);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = {"/{player-id}"})
    public ResponseEntity<MetaDataRes<?>> updatePlayerInfo(@RequestBody PlayerInfoRequest request,
                                                           @PathVariable("player-id") String playerID) {
        MetaDataRes<?> metaDataRes = playerInfoService.updatePlayerInfo(request, playerID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
