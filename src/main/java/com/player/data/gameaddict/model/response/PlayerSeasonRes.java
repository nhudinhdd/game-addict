package com.player.data.gameaddict.model.response;

import com.player.data.gameaddict.entity.PlayerInfo;
import com.player.data.gameaddict.entity.PlayerSeasonBaseInfo;
import com.player.data.gameaddict.entity.Season;
import lombok.Data;

@Data
public class PlayerSeasonRes {

    private String playerSeasonID;
    private SeasonRes seasonRes;
    private PlayerInfoRes playerInfoRes;
    private String playerPosition;
    private String playerSubPosition;
    private String avatar;
    private short salary;
    private short height;
    private short weight;
    private String fitness;
    private String leftFoot;
    private String rightFoot;
    private String skill;
    private short pac;
    private short sho;
    private short pas;
    private short dri;
    private short def;
    private short phy;

    public PlayerSeasonRes(PlayerSeasonBaseInfo info) {
        this.playerSeasonID = info.getPlayerSeasonID();
        this.seasonRes = new SeasonRes(new Season(info.getSeasonID(), info.getShortName(), info.getFullName(), info.getLogo()));
        this.playerInfoRes = new PlayerInfoRes(new PlayerInfo(info.getPlayerID(),info.getShortNamePlayerInfo(), info.getFullNamePlayerInfo(),
                info.getBirthday(), info.getNationID(), info.getNationName(), info.getEnsign()));
        this.playerPosition = info.getPlayerPosition();
        this.playerSubPosition = info.getPlayerSubPosition();
        this.avatar = info.getAvatar();
        this.salary = info.getSalary();
        this.height = info.getHeight();
        this.weight = info.getWeight();
        this.fitness = info.getFitness();
        this.leftFoot = info.getLeftFoot();
        this.rightFoot = info.getRightFoot();
        this.skill = info.getSkill();
        this.pac = info.getPac();
        this.sho = info.getSho();
        this.pas = info.getPas();
        this.dri = info.getDri();
        this.def = info.getDef();
        this.phy = info.getPhy();
    }
}
