package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Continent;
import com.player.data.gameaddict.entity.Nation;
import com.player.data.gameaddict.model.request.NationRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.NationRes;
import com.player.data.gameaddict.repository.ContinentRepository;
import com.player.data.gameaddict.repository.NationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<NationRes> nationResList = new ArrayList<>();
        Optional<Continent> continentOptional = continentRepository.findById(continentID);
        if(continentOptional.isPresent()) {
            nationResList = continentOptional.get().getNations().stream().map(NationRes::new).toList();
        }
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, nationResList);
    }

    public MetaDataRes<?> insertNation(NationRequest nationRequest) throws IOException {
        Optional<Continent> continentOptional = continentRepository.findById(nationRequest.getContinentID());
        if(continentOptional.isEmpty()) {
            log.info("continentOptional empty by continentID = {}", nationRequest.getContinentID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        nationRequest.setEnsign(awsS3Service.upload(nationRequest.getEnsignLogo()));
        Nation nation = new Nation(nationRequest, continentOptional.get(), true);
        nationRepository.save(nation);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateNation(NationRequest nationRequest, String nationID) throws IOException {
        if(!nationRepository.existsById(nationID)) {
            log.info("nationOptional empty by nationID = {}",nationID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        if(!continentRepository.existsById(nationRequest.getContinentID())) {
            log.info("continentOptional empty by continentID = {}", nationRequest.getContinentID());
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        nationRequest.setEnsign(awsS3Service.upload(nationRequest.getEnsignLogo()));
        Nation nation = new Nation(nationRequest, nationRequest.getContinentID(), nationID);
        nationRepository.save(nation);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<NationRes> getNationDetail(String nationID) {
        Optional<Nation> nationOptional = nationRepository.findById(nationID);
        if(nationOptional.isEmpty()) {
            log.info("nationOptional empty by nationID = {}",nationID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        NationRes nationRes = new NationRes(nationOptional.get());
        return  new MetaDataRes<>(MetaDataEnum.SUCCESS, nationRes);
    }
}
