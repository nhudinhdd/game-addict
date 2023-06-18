package com.player.data.gameaddict.controller.management;

import com.player.data.gameaddict.model.request.ContinentRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.ContinentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management/api/continent")
public class ContinentManagerController {

    private final ContinentService continentService;

    public ContinentManagerController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @PostMapping()
    public ResponseEntity<MetaDataRes<?>> insertContinent(@RequestBody ContinentRequest continentRequest) {
        MetaDataRes<?> metaDataRes = continentService.insertContinent(continentRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value ={"/{continent-id}"} )
    public ResponseEntity<MetaDataRes<?>> updateContinent(@RequestBody ContinentRequest continentRequest,
                                                          @PathVariable("continent-id") String continentID) {
        MetaDataRes<?> metaDataRes = continentService.updateContinent(continentRequest, continentID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
