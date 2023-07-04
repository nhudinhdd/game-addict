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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public MetaDataRes<SeasonRes> getSeasonDetail(String seasonID) {
        log.info("Start get season detail with seasonID={}", seasonID);
        Optional<Season> seasonOptional = seasonRepository.findById(seasonID);
        if (seasonOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.SEASON_ID_INVALID);
        log.info("Finish get season detail={}", seasonOptional.get());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new SeasonRes(seasonOptional.get()));
    }

    public MetaDataRes<?> insertSeason(SeasonRequest seasonRequest, MultipartFile logoFile, MultipartFile backgroundLogo, MultipartFile bigLogo) throws IOException {
        boolean isExistsSeason = seasonRepository.existsByShortName(seasonRequest.getShortName());
        if(isExistsSeason) return new MetaDataRes<>(MetaDataEnum.SEASON_SHORT_NAME_ALREADY_EXISTS);

        seasonRequest.setLogo(awsS3Service.upload(logoFile));
        seasonRequest.setBigLogo(awsS3Service.upload(bigLogo));
        seasonRequest.setBackgroundLogo(awsS3Service.upload(backgroundLogo));
        Season season = new Season(seasonRequest, true);
        log.info("Start insert season={}", season);
        seasonRepository.save(season);
        log.info("Finish insert season with seasonID={}", season.getSeasonID());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateSeason(SeasonRequest seasonRequest, String seasonID, MultipartFile logoFile, MultipartFile backgroundLogo, MultipartFile bigLogo) throws IOException {
        Optional<Season> seasonOptional = seasonRepository.findById(seasonID);
        if(seasonOptional.isEmpty()){
            log.warn("Invalid seasonID={}", seasonID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        String logoURL = Objects.isNull(logoFile) ? seasonOptional.get().getLogo() : awsS3Service.upload(logoFile);
        String bigLogoURL = Objects.isNull(bigLogo) ? seasonOptional.get().getBigLogo() : awsS3Service.upload(bigLogo);
        String backgroundLogoUrl = Objects.isNull(backgroundLogo) ? seasonOptional.get().getBackgroundLogo() : awsS3Service.upload(backgroundLogo);
        seasonRequest.setLogo(logoURL);
        seasonRequest.setBackgroundLogo(backgroundLogoUrl);
        seasonRequest.setBigLogo(bigLogoURL);
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
