package com.player.data.gameaddict.model.response;


import com.player.data.gameaddict.common.enums.MetaDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataRes <T> {

    private int code;
    private String message;
    private T data;

    public MetaDataRes(MetaDataEnum metaDataEnum) {
        this.code = metaDataEnum.getCode();
        this.message  = metaDataEnum.getMessage();
    }
    public MetaDataRes(MetaDataEnum metaDataEnum, T data) {
        this.code = metaDataEnum.getCode();
        this.message  = metaDataEnum.getMessage();
        this.data = data;
    }

//    public String getMetaResponse() {
//        return "{code: " + this.code+ " message: "+ this.message + "}";
//    }
}
