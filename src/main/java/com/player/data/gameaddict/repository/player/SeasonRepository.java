package com.player.data.gameaddict.repository.player;

import com.player.data.gameaddict.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, String> {

    boolean existsByShortName(String s);
}
