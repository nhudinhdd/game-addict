package com.player.data.gameaddict.repository;

import com.player.data.gameaddict.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, String> {
}
