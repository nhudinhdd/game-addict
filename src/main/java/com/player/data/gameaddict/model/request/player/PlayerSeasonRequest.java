package com.player.data.gameaddict.model.request.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PlayerSeasonRequest {

    private String playerID;
    private String seasonID;
    private String playerPosition;
    private String playerSubPosition;
    private MultipartFile avatarFile;
    @JsonIgnore
    private String avatar;
    private String reputation;
    private String workRateDef;
    private String workRateAtt;
    private short salary;
    private short height;
    private short weight;
    private String fitness;
    private String leftFoot;
    private String rightFoot;
    private String skill;
    private short speedUp;
    private short speedSprint;
    private short dribbling;
    private short ballControl;
    private short shortPassing;
    private short finishing;
    private short shotPower;
    private short heading;
    private short longShot;
    private short positioning;
    private short vision;
    private short reactions;
    private short volleys;
    private short penalties;
    private short crossing;
    private short longPassing;
    private short freeKick;
    private short curve;
    private short agility;
    private short balance;
    private short marking;
    private short tackleStand;
    private short intercep;
    private short tackleSliding;
    private short strength;
    private short aggression;
    private short jumping;
    private short composure;
    private short gkDiv;
    private short gkHanding;
    private short gkKicking;
    private short gkReactions;
    private short gkPositioning;
    private short pac;
    private short sho;
    private short pas;
    private short dri;
    private short def;
    private short phy;
}
