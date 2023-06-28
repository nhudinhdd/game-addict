package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Continent;
import com.player.data.gameaddict.entity.Nation;
import com.player.data.gameaddict.model.request.player.NationRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.NationRes;
import com.player.data.gameaddict.repository.player.ContinentRepository;
import com.player.data.gameaddict.repository.player.NationRepository;
import com.player.data.gameaddict.service.AwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NationService {

    private final ContinentRepository continentRepository;
    private final NationRepository nationRepository;
    private final AwsS3Service awsS3Service;


    @Autowired
    public NationService(ContinentRepository continentRepository, NationRepository nationRepository, AwsS3Service awsS3Service) {
        this.continentRepository = continentRepository;
        this.nationRepository = nationRepository;
        this.awsS3Service = awsS3Service;
    }

    public MetaDataRes<List<NationRes>> getListNationByContinentID(String continentID) {
        log.info("Start get list nation by continentID = {}", continentID);
        List<NationRes> nationResList = new ArrayList<>();
        if ("".equals(continentID) || continentID == null) {
            List<Nation> nations = nationRepository.findAll();
            if (!CollectionUtils.isEmpty(nations)) nationResList = nations.stream().map(NationRes::new).toList();
        } else {
            Optional<Continent> continentOptional = continentRepository.findById(continentID);
            log.info("Get continent optional with isPresent = {}", continentOptional.isPresent());
            if (continentOptional.isPresent()) {
                nationResList = continentOptional.get().getNations().stream().map(NationRes::new).toList();
            }
        }

        log.info("Finish get list nation with size = {}", nationResList.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, nationResList);
    }

    public MetaDataRes<?> insertNation(NationRequest nationRequest) throws IOException {
        Optional<Continent> continentOptional = continentRepository.findById(nationRequest.getContinentID());
        if (continentOptional.isEmpty()) {
            log.warn("Invalid continentID={}", nationRequest.getContinentID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        nationRequest.setEnsign(awsS3Service.upload(nationRequest.getEnsignLogo()));
        Nation nation = new Nation(nationRequest, continentOptional.get(), true);
        log.info("Start insert nation = {}", nation);
        nationRepository.save(nation);
        log.info("Finish insert nation");
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateNation(NationRequest nationRequest, String nationID) throws IOException {
        if (!nationRepository.existsById(nationID)) {
            log.warn("Invalid nationID = {}", nationID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        if (!continentRepository.existsById(nationRequest.getContinentID())) {
            log.warn("Invalid continentID = {}", nationRequest.getContinentID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        nationRequest.setEnsign(awsS3Service.upload(nationRequest.getEnsignLogo()));
        Nation nation = new Nation(nationRequest, nationRequest.getContinentID(), nationID);
        log.info("Start update nation = {}", nation);
        nationRepository.save(nation);
        log.info("Finish update nation");
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<NationRes> getNationDetail(String nationID) {
        log.info("Start get nation detail by nationID = {}", nationID);
        Optional<Nation> nationOptional = nationRepository.findById(nationID);
        if (nationOptional.isEmpty()) {
            log.info("Invalid nationID = {}", nationID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        NationRes nationRes = new NationRes(nationOptional.get());
        log.info("Finish get nation detail by nationID = {}", nationID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, nationRes);
    }
}
