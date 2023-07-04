package com.player.data.gameaddict.common.enums;

public enum MetaDataEnum {
    SUCCESS(1,  "Success"),
    FILE_ERROR(1001, "File invalid"),
    ID_INVALID(1002, "Id input invalid"),
    SEASON_ID_INVALID(1004, "Season Id input invalid"),
    PLAYER_INFO_ID_INVALID(1005, "player info Id input invalid"),
    TEAM_ID_INVALID(1006, "Team Id input invalid"),
    PLAYER_SEASON_ID_INVALID(1007, "Player season ID input invalid"),
    TRAIT_ID_INVALID(1008, "Trait ID input invalid"),
    DATE_FORMAT_INVALID(1003, "Input string date invalid"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    UNAUTHORIZED(401, "Unauthorized"),
    USER_NAME_EXITS(1004, "User name has been exits"),
    CONTINENT_ID_INVALID(1005, "Continent ID invalid"),
    TOUR_ID_INVALID(1006, "Tournament ID invalid"),
    PLAYER_TEAM_ID_INVALID(1008, "Player Team ID invalid"),
    SEASON_SHORT_NAME_ALREADY_EXISTS(1007, "Season short name already exists"),
    PLAYER_SEASON_ALREADY_EXISTS(1008, "Player Season Invalid"),
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
