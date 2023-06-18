package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.model.response.ContinentRes;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.ContinentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/continent")
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping()
    public ResponseEntity<MetaDataRes<List<ContinentRes>>> getContinents() {
        MetaDataRes<List<ContinentRes>> metaDataRes = continentService.getContinents();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
