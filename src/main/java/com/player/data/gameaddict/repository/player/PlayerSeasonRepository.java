package com.player.data.gameaddict.repository.player;

import com.player.data.gameaddict.entity.PlayerSeason;
import com.player.data.gameaddict.entity.PlayerSeasonBaseInfo;
import com.player.data.gameaddict.entity.PlayerSeasonDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSeasonRepository extends JpaRepository<PlayerSeason, String> {
    PlayerSeasonDetail getPlayerSeasonById(String String);

    @Query(nativeQuery = true,
            value =
            "SELECT pi.player_id as playerID, pi.first_name as firstNamePlayerInfo, pi.last_name as lastNamePlayerInfo, pi.birthday, pi.player_story as playerStory, " +
                    "n.nation_id as nationID, n.nation_name as nationName, n.ensign, n.alt_ensign as altEnsign, n.title_ensign as titleEnsign, n.caption_ensign as captionEnsign, " +
                    "s.season_id as seasonID, s.short_name as shortName,s.logo as logo, s.full_name as fullName, s.logo, s.alt_logo_season as altLogoSeason, s.title_logo_season as titleLogoSeason, s.caption_logo_season as captionLogoSeason, " +
                    "ps.player_season_id as playerSeasonID, ps.salary, ps.player_season_id, ps.player_position as playerPosition, " +
                    "ps.player_sub_position as playerSubPosition, ps.avatar, ps.alt_avatar as altAvatar, ps.title_avatar as titleAvatar, ps.caption_avatar as captionAvatar, " +
                    "ps.fitness, ps.height, ps.weight, " +
                    "ps.left_foot as leftFoot, ps.right_foot as rightFoot, ps.skill, ps.pac, ps.sho, ps.pas, ps.dri, ps.def, ps.phy " +
                    "FROM player_info pi inner join player_season ps " +
                    "on pi.player_id = ps.ft_player_id " +
                    "inner join nation n " +
                    "on pi.nation_id = n.nation_id " +
                    "inner join season s " +
                    "on ps.season_id = s.season_id " +
                    "inner join player_team pt " +
                    "on pi.player_id = pt.player_id " +
                    "inner join playerseason_trait ptr " +
                    "on ps.player_season_id = ptr.player_season_id "+
                    "where (:seasonID is null or :seasonID = '' or s.season_id=:seasonID) " +
                    "and (:teamID is null or  :teamID = '' or pt.team_id=:teamID) " +
                    "and (:traitID is null or  :traitID = '' or ptr.trait_id =:traitID) "+
                    "and pi.last_name like %:playerName%"
    )
    Page<PlayerSeasonBaseInfo> getPlayerSeasons(String playerName,String seasonID, String teamID, String traitID,
                                                Pageable pageable);
}
