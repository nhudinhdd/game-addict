package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.PlayerInfoRes;
import com.player.data.gameaddict.service.PlayerInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/player-info")
public class PlayerInfoController {

    private final PlayerInfoService playerInfoService;

    public PlayerInfoController(PlayerInfoService playerInfoService) {
        this.playerInfoService = playerInfoService;
    }

    @GetMapping
    public ResponseEntity<MetaDataRes<List<PlayerInfoRes>>> getPlayerInfoList(@RequestParam("short-name") String shortName,
                                                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                                                              @RequestParam(value = "page-size", defaultValue = "10") int pageSize) {
        MetaDataList<List<PlayerInfoRes>> metaDataList = playerInfoService.getPlayerInfos(shortName, page, pageSize);
        return new ResponseEntity<>(metaDataList, HttpStatus.OK);
    }
}
