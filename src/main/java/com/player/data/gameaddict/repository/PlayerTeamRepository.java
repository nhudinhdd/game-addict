package com.player.data.gameaddict.repository;

import com.player.data.gameaddict.entity.PlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTeamRepository extends JpaRepository<PlayerTeam, String> {
}
