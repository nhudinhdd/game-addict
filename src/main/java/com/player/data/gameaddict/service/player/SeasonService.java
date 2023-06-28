package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Season;
import com.player.data.gameaddict.model.request.player.SeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.SeasonRes;
import com.player.data.gameaddict.repository.player.SeasonRepository;
import com.player.data.gameaddict.service.AwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final AwsS3Service awsS3Service;

    public SeasonService(SeasonRepository seasonRepository, AwsS3Service awsS3Service) {
        this.seasonRepository = seasonRepository;
        this.awsS3Service = awsS3Service;
    }

    public MetaDataRes<List<SeasonRes>> getListSeason() {
        log.info("Start get list season");
        List<SeasonRes> seasonResList = new ArrayList<>();
        List<Season> seasons = seasonRepository.findAll();
        if (!CollectionUtils.isEmpty(seasons)) {
            seasonResList = seasons.stream().map(SeasonRes::new).toList();
        }
        log.info("Finish get list season with size={}", seasonResList.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, seasonResList);
    }

    public MetaDataRes<?> insertSeason(SeasonRequest seasonRequest) throws IOException {
        seasonRequest.setLogo(awsS3Service.upload(seasonRequest.getLogoFile()));
        Season season = new Season(seasonRequest, true);
        log.info("Start insert season={}", season);
        seasonRepository.save(season);
        log.info("Finish insert season with seasonID={}", season.getSeasonID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateSeason(SeasonRequest seasonRequest, String seasonID) throws IOException {
        Optional<Season> seasonOptional = seasonRepository.findById(seasonID);
        if(seasonOptional.isEmpty()){
            log.warn("Invalid seasonID={}", seasonID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        seasonRequest.setLogo(awsS3Service.upload(seasonRequest.getLogoFile()));
        Season season = new Season(seasonRequest, seasonID);
        log.info("Start update season={}", season);
        seasonRepository.save(season);
        log.info("Finish update seasonID={}", seasonID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> deleteSeason(String seasonID) {
        log.info("Start delete season by id={}", seasonID);
        seasonRepository.deleteById(seasonID);
        log.info("Finish delete season by id={}", seasonID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
