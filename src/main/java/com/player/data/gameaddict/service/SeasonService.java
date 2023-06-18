package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Season;
import com.player.data.gameaddict.model.request.SeasonRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.SeasonRes;
import com.player.data.gameaddict.repository.SeasonRepository;
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
        List<SeasonRes> seasonResList = new ArrayList<>();
        List<Season> seasons = seasonRepository.findAll();
        if (!CollectionUtils.isEmpty(seasons)) {
            log.info("getListSeason empty");
            seasonResList = seasons.stream().map(SeasonRes::new).toList();
        }
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, seasonResList);
    }

    public MetaDataRes<?> insertSeason(SeasonRequest seasonRequest) throws IOException {
        seasonRequest.setLogo(awsS3Service.upload(seasonRequest.getLogoFile()));
        Season season = new Season(seasonRequest, true);
        seasonRepository.save(season);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateSeason(SeasonRequest seasonRequest, String seasonID) throws IOException {
        Optional<Season> seasonOptional = seasonRepository.findById(seasonID);
        if(seasonOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        seasonRequest.setLogo(awsS3Service.upload(seasonRequest.getLogoFile()));
        Season season = new Season(seasonRequest, seasonID);
        seasonRepository.save(season);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> deleteSeason(String seasonID) {
        seasonRepository.deleteById(seasonID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
