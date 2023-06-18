package com.player.data.gameaddict.repository;

import com.player.data.gameaddict.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, String> {
}
