package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.PlayerSeasonTraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerSeasonTraitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/management/player-season-trait")
@RequiredArgsConstructor
public class PlayerSeasonTraitManagerController {

    private final PlayerSeasonTraitService playerSeasonTraitService;

    @PostMapping
    public ResponseEntity<MetaDataRes<?>> insertPlayerSeasonTrait(@RequestBody PlayerSeasonTraitRequest request) {
        MetaDataRes<?> metaDataRes = playerSeasonTraitService.insertPlayerSeasonTrait(request);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
