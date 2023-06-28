package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.*;
import com.player.data.gameaddict.model.request.player.PlayerSeasonTraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.player.PlayerSeasonRepository;
import com.player.data.gameaddict.repository.player.PlayerSeasonTraitRepository;
import com.player.data.gameaddict.repository.player.TraitRepository;
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
            log.info("Invalid playerSeasonID = {}", request.getPlayerSeasonID());
            return new MetaDataRes<>(MetaDataEnum.PLAYER_SEASON_ID_INVALID);
        }

        Optional<Trait> traitOptional = traitRepository.findById(request.getTraitID());
        if(traitOptional.isEmpty()) {
            log.info("Invalid traitID = {}", request.getTraitID());
            return new MetaDataRes<>(MetaDataEnum.TRAIT_ID_INVALID);
        }
        PlayerSeasonTrait playerSeasonTrait = new PlayerSeasonTrait(playerSeasonOptional.get(), traitOptional.get());
        log.info("Start inset playerSeasonTrait={}", playerSeasonTrait);
        playerSeasonTraitRepository.save(playerSeasonTrait);
        log.info("Finish inset playerSeasonTraitID={}", playerSeasonTrait.getPsTraitID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
