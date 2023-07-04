package com.player.data.gameaddict.controller.player.management;

import com.player.data.gameaddict.model.request.player.TeamRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.player.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/management/team")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeamManagementController {

    private final TeamService teamService;
    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> insertTeam(@ModelAttribute TeamRequest teamRequest,
                                                     @RequestParam(name = "teamLogoFile") MultipartFile file) throws IOException {
        MetaDataRes<?> metaDataRes = teamService.insertTeam(teamRequest, file);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PutMapping(value = "{team-id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MetaDataRes<?>> updateTeam(@ModelAttribute TeamRequest teamRequest,
                                                     @PathVariable("team-id") String teamID,
                                                     @RequestParam(name = "teamLogoFile", required = false) MultipartFile file) throws IOException {
        MetaDataRes<?> metaDataRes = teamService.updateTeam(teamRequest, teamID, file);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
