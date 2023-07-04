package com.player.data.gameaddict.repository.player;

import com.player.data.gameaddict.entity.PlayerTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamRepository extends JpaRepository<PlayerTeam, String> {

    @Query("SELECT pt FROM PlayerTeam pt WHERE ((:playerID is null or :playerID in ('') ) or pt.playerInfo.playerID = :playerID)" +
            " and ((:teamID is null or :teamID in ('')) or pt.team.teamID = :teamID)")
    Page<PlayerTeam> findByPlayerIdAndTeamID(@Param("playerID") String playerID, @Param("teamID")  String teamID, Pageable pageable);
}
