package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.request.TournamentRequest;
import com.player.data.gameaddict.model.response.MetaDataRes;
import com.player.data.gameaddict.repository.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public MetaDataRes<?> insertTournament(TournamentRequest request) {
        Tournament tournament = new Tournament(request);
        tournamentRepository.save(tournament);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateTournament(String tourID, TournamentRequest request) {
        Optional<Tournament> tournamentOptional =  tournamentRepository.findById(tourID);
        if(tournamentOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        tournamentOptional.get().setTourName(request.getTourName());
        tournamentRepository.save(tournamentOptional.get());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}