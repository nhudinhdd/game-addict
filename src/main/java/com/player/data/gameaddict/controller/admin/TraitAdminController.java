package com.player.data.gameaddict.controller.admin;

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
@RequestMapping("management/api/trait")
public class TraitAdminController {

    private final TraitService traitService;

    public TraitAdminController(TraitService traitService) {
        this.traitService = traitService;
    }
    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<TraitInsertResponse>> insertTrait(@ModelAttribute TraitRequest traitRequest) {
        MetaDataRes<TraitInsertResponse> metaDataRes = traitService.insertPlayerTrait(traitRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "/{trait-id}")
    public ResponseEntity<?> updateTrait(@PathVariable("trait-id") String traitID, @RequestBody TraitRequest traitRequest) {
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
