package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.TraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.trait.TraitInsertResponse;
import com.player.data.gameaddict.service.player.TraitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/management/trait")
@RequiredArgsConstructor
public class TraitManagementController {

    private final TraitService traitService;

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<TraitInsertResponse>> insertTrait(@ModelAttribute TraitRequest traitRequest) throws IOException {
        MetaDataRes<TraitInsertResponse> metaDataRes = traitService.insertPlayerTrait(traitRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "/{trait-id}")
    public ResponseEntity<?> updateTrait(@PathVariable("trait-id") String traitID, @RequestBody TraitRequest traitRequest) throws IOException {
        MetaDataRes<?> metaDataRes = traitService.updateTrait(traitRequest, traitID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{trait-id}")
    public ResponseEntity<?> deleteTrait(@PathVariable("trait-id") String traitID) {
        // TODO
        MetaDataRes<?> metaDataRes = traitService.deleteTrait(traitID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
