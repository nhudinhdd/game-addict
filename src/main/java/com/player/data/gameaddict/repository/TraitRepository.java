package com.player.data.gameaddict.repository;

import com.player.data.gameaddict.entity.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TraitRepository extends JpaRepository<Trait, String> {
}
