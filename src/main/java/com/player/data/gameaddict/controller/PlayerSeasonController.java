package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.model.response.PlayerSeasonDetailRes;
import com.player.data.gameaddict.model.response.PlayerSeasonRes;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.PlayerSeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/player-season")
public class PlayerSeasonController {

    private final PlayerSeasonService playerSeasonService;

    public PlayerSeasonController(PlayerSeasonService playerSeasonService) {
        this.playerSeasonService = playerSeasonService;
    }

    @GetMapping("/detail")
    public ResponseEntity<MetaDataRes<PlayerSeasonDetailRes>> getPlayerSeasonDetail(@RequestParam("player-season-id") String id) {
        MetaDataRes<PlayerSeasonDetailRes> metaDataRes = playerSeasonService.getPlayerSeasonDetail(id);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MetaDataList<List<PlayerSeasonRes>>> getPlayerSeason(@RequestParam(value = "player-name", defaultValue = "") String playerName,
                                                                        @RequestParam(value = "season-id", defaultValue = "") String seasonID,
                                                                        @RequestParam(value = "team-id", defaultValue = "") String teamID,
                                                                        @RequestParam(value = "trait-id", defaultValue = "") String traitID,
                                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                                        @RequestParam(value = "page-size", defaultValue = "10") int pageSize) {

        MetaDataList<List<PlayerSeasonRes>> metaDataRes = playerSeasonService.getPlayerSeasons(playerName, seasonID,
                teamID, traitID, page, pageSize);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }


}
