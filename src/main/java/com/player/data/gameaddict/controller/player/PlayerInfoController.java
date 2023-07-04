package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataList;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.PlayerInfoRes;
import com.player.data.gameaddict.service.player.PlayerInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player-info")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlayerInfoController {

    private final PlayerInfoService playerInfoService;

    @GetMapping
    public ResponseEntity<MetaDataRes<List<PlayerInfoRes>>> getPlayerInfoList(@RequestParam(value = "last-name", required = false, defaultValue = StringUtils.EMPTY) String lastName,
                                                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                                                              @RequestParam(value = "page-size", defaultValue = "10") int pageSize,
                                                                              @RequestParam(value = "nation-id", required = false) String nationID) {
        MetaDataList<List<PlayerInfoRes>> metaDataList = playerInfoService.getPlayerInfos(lastName, page, pageSize, nationID);
        return new ResponseEntity<>(metaDataList, HttpStatus.OK);
    }

    @GetMapping("{player-info-id}")
    public ResponseEntity<MetaDataRes<PlayerInfoRes>> getPlayerInfoDetail(@PathVariable(value = "player-info-id") String playerInfoID) {
        MetaDataRes<PlayerInfoRes> metaDataRes = playerInfoService.getPlayerInfoDetail(playerInfoID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
