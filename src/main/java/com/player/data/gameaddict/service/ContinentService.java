package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.Continent;
import com.player.data.gameaddict.model.request.ContinentRequest;
import com.player.data.gameaddict.model.response.ContinentRes;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.ContinentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContinentService {

    private final ContinentRepository continentRepository;


    @Autowired
    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public MetaDataRes<List<ContinentRes>> getContinents() {
        log.info("Start get list continent");
        List<ContinentRes> continentRes = new ArrayList<>();
        List<Continent> continents = continentRepository.findAll();
        if (!CollectionUtils.isEmpty(continents)) {
            continentRes = continents.stream().map(ContinentRes::new).toList();
        }
        log.info("Finish get list continent with size = {}", continents.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, continentRes);
    }

    public MetaDataRes<?> insertContinent(ContinentRequest continentRequest) {
        Continent continent = new Continent(continentRequest, true);
        log.info("Start save Continent with continent = {}", continent);
        continentRepository.save(continent);
        log.info("Finish save Continent");
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> updateContinent(ContinentRequest continentRequest, String continentID) {
        Optional<Continent> continentOptional = continentRepository.findById(continentID);
        if(continentOptional.isEmpty()){
            log.warn("Invalid continentID={}", continentID);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        Continent continent = new Continent(continentRequest, continentID);
        log.info("Start update continent with continent = {}", continent);
        continentRepository.save(continent);
        log.info("Finish update Continent");
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
