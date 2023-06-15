package com.player.data.gameaddict.controller;

import com.player.data.gameaddict.model.request.TraitRequest;
import com.player.data.gameaddict.model.response.MetaDataRes;
import com.player.data.gameaddict.model.response.trait.TraitGetResponse;
import com.player.data.gameaddict.model.response.trait.TraitInsertResponse;
import com.player.data.gameaddict.service.TraitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trait")
public class TraitController {

    private final  TraitService traitService;

    public TraitController(TraitService traitService) {
        this.traitService = traitService;
    }

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<TraitGetResponse>>> getTraits() {
        MetaDataRes<List<TraitGetResponse>> metaDataRes = traitService.getTraits();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

}
