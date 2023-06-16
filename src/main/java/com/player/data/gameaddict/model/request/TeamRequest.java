package com.player.data.gameaddict.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TeamRequest {

    private String teamName;
    private MultipartFile teamLogoFile;
    private String tourID;
    @JsonIgnore
    private String teamLogo;
}
