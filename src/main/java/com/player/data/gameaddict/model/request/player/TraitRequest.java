package com.player.data.gameaddict.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TraitRequest {

    private String name;
    private String description;
    private String altLogo;
    private String titleLogo;
    private String captionLogo;
    @JsonIgnore
    private String fileNameTraitLogo;
}
