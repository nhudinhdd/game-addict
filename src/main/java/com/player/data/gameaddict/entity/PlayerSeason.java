package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.PlayerSeasonRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "player_season")
@Data
@NoArgsConstructor
public class PlayerSeason extends BaseEntity {

    @Id
    @Column(name = "player_season_id")
    private String playerSeasonID = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(
            name = "ft_player_id",
            referencedColumnName = "player_id"
    )
    private PlayerInfo playerInfo;

    @ManyToOne
    @JoinColumn(
            name = "season_id",
            referencedColumnName = "season_id"
    )
    private Season season;
    @OneToMany(
            mappedBy = "playerSeason",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<PlayerSeasonTrait> playerSeasonTrait;
    @Column(name = "player_position")
    private String playerPosition;
    @Column(name = "player_sub_position")
    private String playerSubPosition;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "reputation", nullable = false)
    private String reputation;
    @Basic
    @Column(name = "work_rate_def", nullable = false)
    private String workRateDef;
    @Basic
    @Column(name = "work_rate_att", nullable = false)
    private String workRateAtt;
    @Basic
    @Column(name = "salary", nullable = false)
    private short salary;
    @Basic
    @Column(name = "height", nullable = false)
    private short height;
    @Basic
    @Column(name = "weight", nullable = false)
    private short weight;
    @Basic
    @Column(name = "fitness", nullable = false)
    private String fitness;
    @Basic
    @Column(name = "left_foot", nullable = false)
    private String leftFoot;
    @Basic
    @Column(name = "right_foot", nullable = false)
    private String rightFoot;
    @Basic
    @Column(name = "skill", nullable = false)
    private String skill;
    @Basic
    @Column(name = "speed_up", nullable = false)
    private short speedUp;
    @Basic
    @Column(name = "speed_sprint", nullable = false)
    private short speedSprint;
    @Basic
    @Column(name = "dribbling", nullable = false)
    private short dribbling;
    @Basic
    @Column(name = "ball_control", nullable = false)
    private short ballControl;
    @Basic
    @Column(name = "short_passing", nullable = false)
    private short shortPassing;
    @Basic
    @Column(name = "finishing", nullable = false)
    private short finishing;
    @Basic
    @Column(name = "shot_power", nullable = false)
    private short shotPower;
    @Basic
    @Column(name = "heading", nullable = false)
    private short heading;
    @Basic
    @Column(name = "long_shot", nullable = false)
    private short longShot;
    @Basic
    @Column(name = "positioning", nullable = false)
    private short positioning;
    @Basic
    @Column(name = "vision", nullable = false)
    private short vision;
    @Basic
    @Column(name = "reactions", nullable = false)
    private short reactions;
    @Basic
    @Column(name = "volleys", nullable = false)
    private short volleys;
    @Basic
    @Column(name = "penalties", nullable = false)
    private short penalties;
    @Basic
    @Column(name = "crossing", nullable = false)
    private short crossing;
    @Basic
    @Column(name = "long_passing", nullable = false)
    private short longPassing;
    @Basic
    @Column(name = "free_kick", nullable = false)
    private short freeKick;
    @Basic
    @Column(name = "curve", nullable = false)
    private short curve;
    @Basic
    @Column(name = "agility", nullable = false)
    private short agility;
    @Basic
    @Column(name = "balance", nullable = false)
    private short balance;
    @Basic
    @Column(name = "marking", nullable = false)
    private short marking;
    @Basic
    @Column(name = "tackle_stand", nullable = false)
    private short tackleStand;
    @Basic
    @Column(name = "intercep", nullable = false)
    private short intercep;
    @Basic
    @Column(name = "tackle_sliding", nullable = false)
    private short tackleSliding;
    @Basic
    @Column(name = "strength", nullable = false)
    private short strength;
    @Basic
    @Column(name = "aggression", nullable = false)
    private short aggression;
    @Basic
    @Column(name = "jumping", nullable = false)
    private short jumping;
    @Basic
    @Column(name = "composure", nullable = false)
    private short composure;
    @Basic
    @Column(name = "gk_div", nullable = false)
    private short gkDiv;
    @Basic
    @Column(name = "gk_handing", nullable = false)
    private short gkHanding;
    @Basic
    @Column(name = "gk_kicking", nullable = false)
    private short gkKicking;
    @Basic
    @Column(name = "gk_reactions", nullable = false)
    private short gkReactions;
    @Basic
    @Column(name = "gk_positioning", nullable = false)
    private short gkPositioning;
    @Column(name = "pac")
    private short pac;
    @Column(name = "sho")
    private short sho;
    @Column(name = "pas")
    private short pas;
    @Column(name = "dri")
    private short dri;
    @Column(name = "def")
    private short def;
    @Column(name = "phy")
    private short phy;
    @Override
    public String getId() {
        return playerSeasonID;
    }

    public PlayerSeason(PlayerSeasonRequest request, PlayerInfo playerInfo, Season season, boolean isNew) {
        this.playerInfo = playerInfo;
        this.season = season;
        this.playerPosition = request.getPlayerPosition();
        this.playerSubPosition = request.getPlayerSubPosition();
        this.playerPosition = request.getPlayerPosition();
        this.avatar = request.getAvatar();
        this.reputation = request.getReputation();
        this.workRateDef = request.getWorkRateDef();
        this.workRateAtt = request.getWorkRateAtt();
        this.salary = request.getSalary();
        this.height = request.getHeight();
        this.weight = request.getWeight();
        this.fitness = request.getFitness();
        this.leftFoot = request.getLeftFoot();
        this.rightFoot =request.getRightFoot();
        this.skill = request.getSkill();
        this.speedUp =request.getSpeedUp();
        this.speedSprint =request.getSpeedSprint();
        this.dribbling = request.getDribbling();
        this.ballControl = request.getBallControl();
        this.shortPassing = request.getShortPassing();
        this.finishing = request.getFinishing();
        this.shotPower = request.getShotPower();
        this.heading = request.getHeading();
        this.longShot = request.getLongShot();
        this.positioning = request.getPositioning();
        this.vision = request.getVision();
        this.reactions = request.getReactions();
        this.volleys = request.getVolleys();
        this.penalties = request.getPenalties();
        this.crossing = request.getCrossing();
        this.longPassing = request.getLongPassing();
        this.freeKick = request.getFreeKick();
        this.curve = request.getCurve();
        this.agility = request.getAgility();
        this.balance = request.getBalance();
        this.marking = request.getMarking();
        this.tackleStand = request.getTackleStand();
        this.intercep = request.getIntercep();
        this.tackleSliding = request.getTackleSliding();
        this.strength = request.getStrength();
        this.aggression = request.getAggression();
        this.jumping = request.getJumping();
        this.composure = request.getComposure();
        this.gkDiv = request.getGkDiv();
        this.gkHanding = request.getGkHanding();
        this.gkKicking = request.getGkKicking();
        this.gkReactions = request.getGkReactions();
        this.gkPositioning = request.getGkPositioning();
        this.pac = request.getPac();
        this.sho = request.getSho();
        this.pas = request.getPas();
        this.dri = request.getDri();
        this.def = request.getDef();
        this.phy = request.getPhy();
        setNew(isNew);
    }
}
