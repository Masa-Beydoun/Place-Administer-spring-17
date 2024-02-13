//package com.example.PlaceAdminister.Security;
//
//import com.google.common.collect.Sets;
//
//import java.util.Set;
//
//import static com.example.PlaceAdminister.Security.ApplicationUserPermission.*;
//
//public enum ApplicationUserRole {
//    SUPER_ADMIN(Sets.newHashSet(SUPER_ADMIN_READ,SUPER_ADMIN_WRITE)),
//    ADMIN(Sets.newHashSet(ADMIN_READ,ADMIN_WRITE)),
//    USER(Sets.newHashSet(USER_READ));
//    private  final Set<ApplicationUserPermission> permissions;
//
//    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
//        this.permissions=permissions;
//    }
//
//    public Set<ApplicationUserPermission> getPermissions() {
//        return permissions;
//    }
//}
//
