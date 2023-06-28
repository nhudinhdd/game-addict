package com.player.data.gameaddict.repository.player;

import com.player.data.gameaddict.entity.PlayerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, String> {
    Page<PlayerInfo> findAllByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
//    Page<PlayerInfo> findMine(Pageable pageable);

}
