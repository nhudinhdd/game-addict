package com.player.data.gameaddict.controller.management;

import com.amazonaws.Response;
import com.player.data.gameaddict.model.request.PlayerSeasonTraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.PlayerSeasonTraitService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/player-season-trait")
public class PlayerSeasonTraitManagerController {

    private final PlayerSeasonTraitService playerSeasonTraitService;

    public PlayerSeasonTraitManagerController(PlayerSeasonTraitService playerSeasonTraitService) {
        this.playerSeasonTraitService = playerSeasonTraitService;
    }

    @PostMapping
    public ResponseEntity<MetaDataRes<?>> insertPlayerSeasonTrait(@RequestBody PlayerSeasonTraitRequest request) {
        MetaDataRes<?> metaDataRes = playerSeasonTraitService.insertPlayerSeasonTrait(request);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
