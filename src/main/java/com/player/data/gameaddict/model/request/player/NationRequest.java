package com.player.data.gameaddict.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NationRequest {

    private String continentID;
    private String nationName;
    @JsonIgnore
    private String ensign;
    @JsonIgnore
    private MultipartFile ensignLogo;
    private String altEnsign;
    private String titleEnsign;
    private String captionEnsign;
}
