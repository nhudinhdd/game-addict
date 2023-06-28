package com.player.data.gameaddict.repository.player;

import com.player.data.gameaddict.entity.PlayerSeasonTrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSeasonTraitRepository extends JpaRepository<PlayerSeasonTrait, String> {
}
