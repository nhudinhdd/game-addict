package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.NationRequest;
import com.player.data.gameaddict.model.request.player.NationUpdateRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/management/nation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",
        methods = {RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.GET},
        allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "multipart/form-data"},
        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
public class NationManagementController {

    private final NationService nationService;

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> insetNation(@ModelAttribute NationRequest nationRequest) throws IOException {
        MetaDataRes<?> metaDataRes = nationService.insertNation(nationRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "/{nation-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> updateNation(@ModelAttribute NationUpdateRequest nationRequest,
                                                       @PathVariable("nation-id") String nationID,
                                                        @RequestParam(name = "ensignLogo", required = false) MultipartFile file) throws IOException {
        MetaDataRes<?> metaDataRes = nationService.updateNation(nationRequest, nationID, file);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
