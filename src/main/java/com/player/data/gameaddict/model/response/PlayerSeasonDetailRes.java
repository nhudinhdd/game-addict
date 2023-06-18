package com.player.data.gameaddict.model.response;

import com.player.data.gameaddict.entity.PlayerSeasonDetail;
import lombok.Data;

@Data
public class PlayerSeasonDetailRes {

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

    public PlayerSeasonDetailRes(PlayerSeasonDetail detail) {
        this.speedUp = detail.getSpeedUp();
        this.speedSprint = detail.getSpeedSprint();
        this.dribbling = detail.getDribbling();
        this.ballControl = detail.getBallControl();
        this.shortPassing = detail.getShortPassing();
        this.finishing = detail.getFinishing();
        this.shotPower = detail.getShotPower();
        this.heading = detail.getHeading();
        this.longShot = detail.getLongShot();
        this.positioning = detail.getPositioning();
        this.vision = detail.getVision();
        this.reactions = detail.getReactions();
        this.volleys = detail.getVolleys();
        this.penalties = detail.getPenalties();
        this.crossing = detail.getCrossing();
        this.longPassing = detail.getLongPassing();
        this.freeKick = detail.getFreeKick();
        this.curve = detail.getCurve();
        this.agility = detail.getAgility();
        this.balance = detail.getBalance();
        this.marking = detail.getMarking();
        this.tackleStand = detail.getTackleStand();
        this.intercep = detail.getIntercep();
        this.tackleSliding = detail.getTackleSliding();
        this.strength = detail.getStrength();
        this.aggression = detail.getAggression();
        this.jumping = detail.getJumping();
        this.composure = detail.getComposure();
        this.gkDiv = detail.getGkDiv();
        this.gkHanding = detail.getGkHanding();
        this.gkKicking = detail.getGkKicking();
        this.gkReactions = detail.getGkReactions();
        this.gkPositioning = detail.getGkPositioning();
    }
}
