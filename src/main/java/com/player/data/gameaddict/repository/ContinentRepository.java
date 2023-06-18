package com.player.data.gameaddict.repository;

import com.player.data.gameaddict.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, String> {
}
