package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.NationRes;
import com.player.data.gameaddict.service.player.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/nation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NationController {

    private final NationService nationService;

    @GetMapping(value = "/detail/{nation-id}")
    public ResponseEntity<MetaDataRes<NationRes>> getNationDetail(@PathVariable("nation-id") String nationID){
        MetaDataRes<NationRes> metaDataRes = nationService.getNationDetail(nationID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<MetaDataRes<List<NationRes>>> getNation(@RequestParam(value = "continent-id", required = false) String continentID){
        MetaDataRes<List<NationRes>> metaDataRes = nationService.getListNationByContinentID(continentID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
