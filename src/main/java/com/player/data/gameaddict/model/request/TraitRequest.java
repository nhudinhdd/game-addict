package com.player.data.gameaddict.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TraitRequest {

    private String name;
    private String description;
    private MultipartFile traitLogo;
    @JsonIgnore
    private String fileNameTraitLogo;
}
