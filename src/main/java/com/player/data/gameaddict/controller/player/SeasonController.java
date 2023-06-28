package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.SeasonRes;
import com.player.data.gameaddict.service.player.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/season")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService service;

    @GetMapping("")
    public ResponseEntity<MetaDataRes<List<SeasonRes>>> getListSeason() {
        MetaDataRes<List<SeasonRes>> seaListMetaDataRes = service.getListSeason();
        return new ResponseEntity<>(seaListMetaDataRes, HttpStatus.OK);
    }
}
