package com.player.data.gameaddict.entity;

import java.time.LocalDate;

public interface PlayerSeasonBaseInfo {


    String getPlayerID();

    String getFirstNamePlayerInfo();

    String getLastNamePlayerInfo();

    LocalDate getBirthday();
    String getPlayerStory();

    String getNationID();

    String getNationName();

    String getEnsign();

    String getSeasonID();

    String getShortName();

    String getFullName();

    String getLogo();


    String getPlayerSeasonID();

    String getPlayerPosition();

    String getPlayerSubPosition();

    String getAvatar();

    short getSalary();

    short getHeight();

    short getWeight();

    String getFitness();

    String getLeftFoot();

    String getRightFoot();

    String getSkill();

    short getPac();

    short getSho();

    short getPas();

    short getDri();

    short getDef();

    short getPhy();
    String getAltEnsign();
    String getTitleEnsign();
    String getCaptionEnsign();
    String getAltLogoSeason();
    String getTitleLogoSeason();
    String getCaptionLogoSeason();
    String getAltAvatar();
    String getTitleAvatar();
    String getCaptionAvatar();
}
