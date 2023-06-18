package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.NationRes;
import com.player.data.gameaddict.service.NationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/nation")
public class NationController {

    private final NationService nationService;

    public NationController(NationService nationService) {
        this.nationService = nationService;
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<MetaDataRes<NationRes>> getNationDetail(@RequestParam("nation-id") String nationID){
        MetaDataRes<NationRes> metaDataRes = nationService.getNationDetail(nationID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<NationRes>>> getNation(@RequestParam("continent-id") String continentID){
        MetaDataRes<List<NationRes>> metaDataRes = nationService.getListNationByContinentID(continentID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
