package com.player.data.gameaddict.common.enums;

public enum ApplicationPermission {

    MANAGER_CREATE("manager:create"),
    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_DELETE("manager:delete");

    private final String permission;
    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
