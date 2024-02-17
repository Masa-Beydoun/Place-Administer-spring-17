package com.example.PlaceAdminister.Security;

public enum ApplicationUserPermission {
    PLACE_READ("super_admin:read"),
    PLACE_WRITE("super_admin:write"),
   ROOM_READ("admin:read"),
    ROOM_WRITE("admin:write"),
    TABLE_READ("user:read"),
    TABLE_WRITE("admin:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
