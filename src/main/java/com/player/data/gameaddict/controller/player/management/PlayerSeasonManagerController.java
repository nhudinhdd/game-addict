package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.PlayerSeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/management/player-season")
@RequiredArgsConstructor
public class PlayerSeasonManagerController {

    private final PlayerSeasonService playerSeasonService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MetaDataRes<?>> insertPlayerSeason(@ModelAttribute PlayerSeasonRequest playerSeasonRequest) throws IOException {
        MetaDataRes<?> metaDataRes = playerSeasonService.insertPlayerSeason(playerSeasonRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
