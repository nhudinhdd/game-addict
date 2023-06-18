package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.*;
import com.player.data.gameaddict.model.request.PlayerSeasonTraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.PlayerSeasonRepository;
import com.player.data.gameaddict.repository.PlayerSeasonTraitRepository;
import com.player.data.gameaddict.repository.TraitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PlayerSeasonTraitService {

    private final PlayerSeasonTraitRepository playerSeasonTraitRepository;
    private final PlayerSeasonRepository playerSeasonRepository;
    private final TraitRepository traitRepository;

    public PlayerSeasonTraitService(PlayerSeasonTraitRepository playerSeasonTraitRepository,
                                    PlayerSeasonRepository playerSeasonRepository,
                                    TraitRepository traitRepository) {
        this.playerSeasonTraitRepository = playerSeasonTraitRepository;
        this.playerSeasonRepository = playerSeasonRepository;
        this.traitRepository = traitRepository;
    }

    public MetaDataRes<?> insertPlayerSeasonTrait(PlayerSeasonTraitRequest request) {
        Optional<PlayerSeason> playerSeasonOptional = playerSeasonRepository.findById(request.getPlayerSeasonID());
        if(playerSeasonOptional.isEmpty()) {
            log.info("playerSeasonOptional empty with id = {}", request.getPlayerSeasonID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_SEASON_ID_INVALID);
        }

        Optional<Trait> traitOptional = traitRepository.findById(request.getTraitID());
        if(traitOptional.isEmpty()) {
            log.info("traitOptional empty with id = {}", request.getTraitID());
            return new MetaDataRes<>(MetaDataEnum.TRAIT_ID_INVALID);
        }

        PlayerSeasonTrait playerSeasonTrait = new PlayerSeasonTrait(playerSeasonOptional.get(), traitOptional.get());
        playerSeasonTraitRepository.save(playerSeasonTrait);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
