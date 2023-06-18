package com.player.data.gameaddict.controller.management;

import com.player.data.gameaddict.model.request.NationRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.NationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("management/api/nation")
public class NationManagementController {

    private final NationService nationService;

    public NationManagementController(NationService nationService) {
        this.nationService = nationService;
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> insetNation(@ModelAttribute NationRequest nationRequest) throws IOException {
        MetaDataRes<?> metaDataRes = nationService.insertNation(nationRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "/{nation-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> updateNation(@ModelAttribute NationRequest nationRequest,
                                                       @PathVariable("nation-id") String nationID) throws IOException {
        MetaDataRes<?> metaDataRes = nationService.updateNation(nationRequest, nationID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
