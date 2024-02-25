package com.example.PlaceAdminister.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.PlaceAdminister.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    SUPER_ADMIN(Sets.newHashSet(PLACE_READ,PLACE_WRITE)),
    ADMIN(Sets.newHashSet(ROOM_WRITE,ROOM_READ,TABLE_WRITE,TABLE_READ)),
    USER(Sets.newHashSet(ROOM_READ,TABLE_READ,PLACE_READ));
    private  final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions=permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}

