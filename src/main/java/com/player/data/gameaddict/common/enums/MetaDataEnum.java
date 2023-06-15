package com.player.data.gameaddict.common.enums;

public enum MetaDataEnum {
    SUCCESS(1,  "Success"),
    FILE_ERROR(1001, "File invalid"),
    ID_INVALID(1002, "Id input invalid"),
    ;
    private int code;
    private String message;

    MetaDataEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
