package com.player.data.gameaddict.controller.management;

import com.player.data.gameaddict.model.request.SeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("management/api/season")
public class SeasonManagementController {

    private final SeasonService service;

    @Autowired
    public SeasonManagementController(SeasonService teamService) {
        this.service = teamService;
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> insertTeam(@ModelAttribute SeasonRequest seasonRequest) throws IOException {
        MetaDataRes<?> metaDataRes = service.insertSeason(seasonRequest);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "{season-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> updateTeam(@ModelAttribute SeasonRequest seasonRequest, @PathVariable("season-id") String seasonID) throws IOException {
        MetaDataRes<?> metaDataRes = service.updateSeason(seasonRequest, seasonID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @DeleteMapping(value = "{season-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> deleteSeason(@PathVariable("season-id") String seasonID) {
        MetaDataRes<?> metaDataRes = service.deleteSeason(seasonID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}