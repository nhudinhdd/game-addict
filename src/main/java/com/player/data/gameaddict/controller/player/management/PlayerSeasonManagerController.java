package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.PlayerSeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/management/player-season")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlayerSeasonManagerController {

    private final PlayerSeasonService playerSeasonService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MetaDataRes<?>> insertPlayerSeason(@ModelAttribute PlayerSeasonRequest playerSeasonRequest,
                                                             @RequestParam("player-season-avatar")MultipartFile playerSeasonAvatar) throws Exception {
        MetaDataRes<?> metaDataRes = playerSeasonService.insertPlayerSeason(playerSeasonRequest, playerSeasonAvatar);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "{player-season-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MetaDataRes<?>> updatePlayerSeason(@ModelAttribute PlayerSeasonRequest playerSeasonRequest,
                                                             @PathVariable("player-season-id") String playerSeasonID,
                                                             @RequestParam(value = "player-season-avatar", required = false)MultipartFile playerSeasonAvatar) throws Exception {
        MetaDataRes<?> metaDataRes = playerSeasonService.updatePlayerSeason(playerSeasonRequest, playerSeasonID, playerSeasonAvatar);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
