package com.player.data.gameaddict.entity;

import com.player.data.gameaddict.model.request.player.PlayerInfoRequest;
import com.player.data.gameaddict.util.DateUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "player_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfo extends BaseEntity {

    @Id
    @Column(name = "player_id")
    private String playerID = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(
            name = "nation_id",
            referencedColumnName = "nation_id"
    )
    private Nation nation;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "player_story")
    private String playerStory;

    @OneToMany(
            mappedBy = "playerInfo",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<PlayerSeason> playerSeasons;
    @OneToMany(
            mappedBy = "playerInfo",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<PlayerTeam> playerTeams;

    public String getId() {
        return playerID;
    }

    public PlayerInfo(String playerID, String shortName, String fullName, LocalDate birthday, String nationID,
                      String nationName, String ensign, String altEnsign, String titleEnsign, String captionEnsign) {
        this.playerID = playerID;
        this.firstName = shortName;
        this.lastName = fullName;
        this.birthday = birthday;
        this.nation =  new Nation(nationID, nationName,ensign, altEnsign, titleEnsign, captionEnsign );
    }
    public PlayerInfo(PlayerInfoRequest request, Nation nation, boolean isNew) {
        this.nation = nation;
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.birthday = DateUtil.stringToLocalDate(request.getBirthday());
        setNew(isNew);
    }

    public PlayerInfo(PlayerInfoRequest request, Nation nation, String playerID) {
        this.nation = nation;
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.birthday = DateUtil.stringToLocalDate(request.getBirthday());
        this.playerID = playerID;
        this.playerStory = request.getPlayerStory();
    }
}
