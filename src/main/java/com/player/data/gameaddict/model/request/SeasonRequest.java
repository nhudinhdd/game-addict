package com.player.data.gameaddict.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SeasonRequest {

    private String shortName;
    private String fullName;
    private MultipartFile logoFile;
    @JsonIgnore
    private String logo;
}
