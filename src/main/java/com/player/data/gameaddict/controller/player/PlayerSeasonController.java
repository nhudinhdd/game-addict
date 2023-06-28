package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.player.PlayerSeasonDetailRes;
import com.player.data.gameaddict.model.response.player.PlayerSeasonRes;
import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.PlayerSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player-season")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlayerSeasonController {

    private final PlayerSeasonService playerSeasonService;

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
