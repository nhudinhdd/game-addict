package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.SeasonRes;
import com.player.data.gameaddict.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/season")
public class SeasonController {

    private final SeasonService service;

    @Autowired
    public SeasonController(SeasonService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<MetaDataRes<List<SeasonRes>>> getListSeason() {
        MetaDataRes<List<SeasonRes>> seaListMetaDataRes = service.getListSeason();
        return new ResponseEntity<>(seaListMetaDataRes, HttpStatus.OK);
    }
}
