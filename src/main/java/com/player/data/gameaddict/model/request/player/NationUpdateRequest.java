package com.player.data.gameaddict.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class NationUpdateRequest {
    private String continentID;
    private String nationName;
    @JsonIgnore
    private String ensign;
    private String altEnsign;
    private String titleEnsign;
    private String captionEnsign;
}
