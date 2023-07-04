package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.SeasonRes;
import com.player.data.gameaddict.service.player.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/season")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SeasonController {

    private final SeasonService service;

    @GetMapping("")
    public ResponseEntity<MetaDataRes<List<SeasonRes>>> getListSeason() {
        MetaDataRes<List<SeasonRes>> seaListMetaDataRes = service.getListSeason();
        return new ResponseEntity<>(seaListMetaDataRes, HttpStatus.OK);
    }

    @GetMapping("{season-id}")
    public ResponseEntity<MetaDataRes<SeasonRes>> getSeasonDetail(@PathVariable("season-id") String seasonID) {
        MetaDataRes<SeasonRes> metaDataRes = service.getSeasonDetail(seasonID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
