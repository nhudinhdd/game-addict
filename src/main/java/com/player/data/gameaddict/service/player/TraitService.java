package com.player.data.gameaddict.service.player;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.config.AwsCloudConfigValue;
import com.player.data.gameaddict.entity.Trait;
import com.player.data.gameaddict.model.request.player.TraitRequest;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.model.response.player.TraitResponse;
import com.player.data.gameaddict.repository.player.TraitRepository;
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

    public MetaDataRes<?> insertPlayerTrait(TraitRequest traitRequest, MultipartFile traitLogo) throws IOException {
        log.info("Start insert player trait with name = {}, description = {}", traitRequest.getName(), traitRequest.getDescription());
        MetaDataRes<?> metaDataRes;
        traitRequest.setFileNameTraitLogo(awsS3Service.upload(traitLogo));
        Trait trait = new Trait(traitRequest, true);
        traitRepository.save(trait);
        metaDataRes = new MetaDataRes<>(MetaDataEnum.SUCCESS);
        log.info("Finish insert player trait metaDataRes = {}", metaDataRes);
        return metaDataRes;
    }

    public MetaDataRes<List<TraitResponse>> getTraits() {
        log.info("Start get traits");
        List<TraitResponse> traitRespons = new ArrayList<>();
        List<Trait> traits = traitRepository.findAll();
        if (!CollectionUtils.isEmpty(traits)) {
            traitRespons = traits.stream().map(TraitResponse::new).collect(Collectors.toList());
        }
        log.info("Finish get traits with size={}", traitRespons.size());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, traitRespons);
    }

    public MetaDataRes<TraitResponse> getTraitDetail(String traitID) {
        log.info("Start get trait detail with traitID={}", traitID);
        Optional<Trait> traitOptional = traitRepository.findById(traitID);
        if (traitOptional.isEmpty()) {
            return new MetaDataRes<>(MetaDataEnum.TRAIT_ID_INVALID);
        }
        log.info("Finish get trait detail with traitRes={}", traitOptional.get());
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new TraitResponse(traitOptional.get()));
    }

    public MetaDataRes<?> updateTrait(TraitRequest traitRequest, String traitID, MultipartFile traitLogo) throws IOException {
        Optional<Trait> traitOptional = traitRepository.findById(traitID);
        if (traitOptional.isEmpty()) return new MetaDataRes<>(MetaDataEnum.TRAIT_ID_INVALID);
        traitRequest.setFileNameTraitLogo(Objects.isNull(traitLogo) ? traitOptional.get().getLogo() : awsS3Service.upload(traitLogo));
        Trait trait = new Trait(traitRequest, traitID);
        log.info("Start update trait={}", trait);
        traitRepository.save(trait);
        log.info("Finish update trait with traitID={}", traitID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }

    public MetaDataRes<?> deleteTrait(String traitID) {
        log.info("Start delete trait with traitID={}", traitID);
        traitRepository.deleteById(traitID);
        log.info("Finish delete trait with traitID={}", traitID);
        return new MetaDataRes<>(MetaDataEnum.SUCCESS);
    }
}
