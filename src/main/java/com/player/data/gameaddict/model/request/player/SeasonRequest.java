package com.player.data.gameaddict.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SeasonRequest {

    private String shortName;
    private String fullName;
    private String altLogo;
    private String titleLogo;
    private String captionLogo;
    private String altBackgroundLogo;
    private String titleBackgroundLogo;
    private String captionBackgroundLogo;
    @JsonIgnore
    private String logo;
    @JsonIgnore
    private String backgroundLogo;
    @JsonIgnore
    private String bigLogo;
}
