package com.player.data.gameaddict.controller.player;

import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.trait.TraitGetResponse;
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
    public ResponseEntity<MetaDataRes<List<TraitGetResponse>>> getTraits() {
        MetaDataRes<List<TraitGetResponse>> metaDataRes = traitService.getTraits();
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

}
