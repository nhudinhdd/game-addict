package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.PlayerInfoRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/management/player-info")
@RequiredArgsConstructor
public class PlayerInfoManagerController {

    private final PlayerInfoService playerInfoService;
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
