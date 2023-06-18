package com.player.data.gameaddict.model.response.common;

import com.player.data.gameaddict.common.enums.MetaDataEnum;
import lombok.Data;

@Data
public class MetaDataList<T> extends MetaDataRes<T> {
    private int totalPage;

    public MetaDataList(MetaDataEnum metaDataEnum, T data, int totalPage) {
        super(metaDataEnum, data);
        this.totalPage = totalPage;
    }
}
