package com.player.data.gameaddict.repository;

import com.player.data.gameaddict.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<Nation, String> {
}
