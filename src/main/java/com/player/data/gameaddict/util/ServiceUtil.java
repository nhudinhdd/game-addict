package com.player.data.gameaddict.util;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ServiceUtil {

    public MetaDataRes<?> checkOptionalByID(String id, JpaRepository<?, String> jpa) {
        Optional<?> optional = jpa.findById(id);
        if (optional.isEmpty()) {
            log.info("nationOptional empty with nationID = {}", id);
            return new MetaDataRes<>(MetaDataEnum.ID_INVALID);
        }
        return null;
    }
}
