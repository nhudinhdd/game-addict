package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.player.ContinentRes;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.NationRes;
import com.player.data.gameaddict.service.player.ContinentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/continent")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContinentController {

    private final ContinentService continentService;

    @GetMapping()
    public ResponseEntity<MetaDataRes<List<ContinentRes>>> getContinents() {
        MetaDataRes<List<ContinentRes>> metaDataRes = continentService.getContinents();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @GetMapping("/{continent-id}")
    public ResponseEntity<MetaDataRes<ContinentRes>> getContinentDetail( @PathVariable("continent-id") String continentID) {
        MetaDataRes<ContinentRes> metaDataRes = continentService.getContinentDetail(continentID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
