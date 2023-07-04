package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.SeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/management/season")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SeasonManagementController {

    private final SeasonService service;

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> insertTeam(@ModelAttribute SeasonRequest seasonRequest,
                                                     @RequestParam("season-logo") MultipartFile logoFile,
                                                     @RequestParam(value = "season-big-logo", required = false) MultipartFile bigLogo,
                                                     @RequestParam("season-background-logo") MultipartFile backgroundLogo) throws IOException {
        MetaDataRes<?> metaDataRes = service.insertSeason(seasonRequest, logoFile, backgroundLogo, bigLogo);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "{season-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> updateTeam(@ModelAttribute SeasonRequest seasonRequest,
                                                     @PathVariable("season-id") String seasonID,
                                                     @RequestParam(value = "season-logo", required = false) MultipartFile logoFile,
                                                     @RequestParam(value = "season-big-logo", required = false) MultipartFile bigLogo,
                                                     @RequestParam(value = "season-background-logo", required = false) MultipartFile backgroundLogo) throws IOException {
        MetaDataRes<?> metaDataRes = service.updateSeason(seasonRequest, seasonID, logoFile, backgroundLogo, bigLogo);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @DeleteMapping(value = "{season-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> deleteSeason(@PathVariable("season-id") String seasonID) {
        MetaDataRes<?> metaDataRes = service.deleteSeason(seasonID);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
