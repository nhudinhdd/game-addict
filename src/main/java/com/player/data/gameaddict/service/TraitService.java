package com.player.data.gameaddict.service;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.config.AwsCloudConfigValue;
import com.player.data.gameaddict.entity.Trait;
import com.player.data.gameaddict.model.request.TraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.trait.TraitGetResponse;
import com.player.data.gameaddict.model.response.trait.TraitInsertResponse;
import com.player.data.gameaddict.repository.TraitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TraitService {

    private final AwsS3Service awsS3Service;
    private final TraitRepository traitRepository;
    private final AwsCloudConfigValue awsCloudConfigValue;

    public TraitService(AwsS3Service awsS3Service, TraitRepository traitRepository, AwsCloudConfigValue awsCloudConfigValue) {
        this.awsS3Service = awsS3Service;
        this.traitRepository = traitRepository;
        this.awsCloudConfigValue = awsCloudConfigValue;
    }

    public MetaDataRes<TraitInsertResponse> insertPlayerTrait(TraitRequest traitRequest) throws IOException {
        log.info("Start insert player trait with name = {}, description = {}", traitRequest.getName(), traitRequest.getDescription());
        MetaDataRes<TraitInsertResponse> metaDataRes;
        String fileNameS3 = awsS3Service.upload(traitRequest.getTraitLogo());
        traitRequest.setFileNameTraitLogo(fileNameS3);
        Trait trait = new Trait(traitRequest, true);
        traitRepository.save(trait);
        metaDataRes = new MetaDataRes<>(MetaDataEnum.SUCCESS, new TraitInsertResponse(fileNameS3));
        log.info("Finish insert player trait metaDataRes = {}", metaDataRes);
        return metaDataRes;
    }

    public MetaDataRes<List<TraitGetResponse>> getTraits() {
        List<TraitGetResponse> traitGetResponses = new ArrayList<>();
        List<Trait> traits = traitRepository.findAll();
        if (!CollectionUtils.isEmpty(traits)) {
            traitGetResponses = traits.stream().map(trait -> new TraitGetResponse(trait, awsCloudConfigValue.getBaseURL())).collect(Collectors.toList());
        }
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, traitGetResponses);
    }

    public MetaDataRes<?> updateTrait(TraitRequest traitRequest, String traitID) throws IOException {
        Optional<Trait> traitOptional = traitRepository.findById(traitID);
        MetaDataRes<?> metaDataRes;
        if (traitOptional.isPresent()) {
            String fileNameS3 = awsS3Service.upload(traitRequest.getTraitLogo());
            Trait trait = traitOptional.get();
            trait.setDescription(traitRequest.getDescription());
            trait.setLogo(fileNameS3);
            trait.setName(traitRequest.getName());
            traitRepository.save(trait);
            metaDataRes = new MetaDataRes<>(MetaDataEnum.SUCCESS);
        } else {
            metaDataRes = new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        return metaDataRes;
    }

    public MetaDataRes<?> deleteTrait(String traitID) {
        traitRepository.deleteById(traitID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
