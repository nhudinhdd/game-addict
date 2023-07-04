package com.player.data.gameaddict.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class TeamRequest {

    private String teamName;

    private String altLogo;
    private String titleLogo;
    private String captionLogo;
    private String tourID;
    @JsonIgnore
    private String teamLogo;
}
