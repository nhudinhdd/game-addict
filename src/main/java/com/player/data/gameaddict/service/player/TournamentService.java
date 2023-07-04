package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Team;
import com.player.data.gameaddict.entity.Tournament;
import com.player.data.gameaddict.model.request.player.TournamentRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.TournamentRes;
import com.player.data.gameaddict.repository.player.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public MetaDataRes<?> insertTournament(TournamentRequest request) {
        Tournament tournament = new Tournament(request, true);
        tournamentRepository.save(tournament);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateTournament(String tourID, TournamentRequest request) {
        Optional<Tournament> tournamentOptional =  tournamentRepository.findById(tourID);
        if(tournamentOptional.isEmpty()) {
            log.warn("Invalid tourID={}", tourID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        tournamentOptional.get().setTourName(request.getTourName());
        log.info("Start update tournament={}", tournamentOptional.get());
        tournamentRepository.save(tournamentOptional.get());
        log.info("Start update tournament with tourID={}", tournamentOptional.get().getTourID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<List<TournamentRes>> getTournaments() {
        log.info("Start get tournament");
        List<TournamentRes> tournamentRes = new ArrayList<>();
        List<Tournament> tournaments =  tournamentRepository.findAll();
        if(!CollectionUtils.isEmpty(tournaments))
            tournamentRes = tournaments.stream().map(TournamentRes::new).toList();
        log.info("Finish get tournament with size={}", tournamentRes.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, tournamentRes);
    }

    public MetaDataRes<TournamentRes> getTournamentDetail(String tourId) {
        log.info("Start get tournament detail with tourId={}", tourId);
        Optional<Tournament> tournamentOptional =  tournamentRepository.findById(tourId);
        if(tournamentOptional.isEmpty())
            return new MetaDataRes<>(MetaDataEnum.TOUR_ID_INVALID);
        log.info("Finish get tournament={}", tournamentOptional.get());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new TournamentRes(tournamentOptional.get()));
    }

    public MetaDataRes<List<Team>> getTeamByTournamentID(String tourID) {
        log.info("Start get team by tourID={}", tourID);
        List<Team> teams = new ArrayList<>();
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tourID);
        if(tournamentOptional.isPresent())
            teams = tournamentOptional.get().getTeams();
        log.info("Finish get team with size={}", teams.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, teams);
    }
}