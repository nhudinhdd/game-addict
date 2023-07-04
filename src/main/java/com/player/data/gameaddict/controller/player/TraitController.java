package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.TraitResponse;
import com.player.data.gameaddict.service.player.TraitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trait")
@RequiredArgsConstructor
public class TraitController {

    private final  TraitService traitService;

    @GetMapping(value = "")
    public ResponseEntity<MetaDataRes<List<TraitResponse>>> getTraits() {
        MetaDataRes<List<TraitResponse>> metaDataRes = traitService.getTraits();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @GetMapping(value = "{trait-id}")
    public ResponseEntity<MetaDataRes<TraitResponse>> getTraitDetail(@PathVariable("trait-id") String traitID) {
        MetaDataRes<TraitResponse> metaDataRes = traitService.getTraitDetail(traitID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
