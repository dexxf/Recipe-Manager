package com.io.dexxf.domain.auth.valueobject;

import java.util.Set;

public enum Roles {

    USER(Set.of(
            Permission.RECIPE_CREATE,
            Permission.RECIPE_VIEW,
            Permission.RECIPE_UPDATE,
            Permission.RECIPE_DELETE,
            Permission.RECIPE_SHARE,
            Permission.RECIPE_SEARCH,
            Permission.RECIPE_FAVORITE
    )),

    ADMIN(Set.of(
            Permission.values()
    ));

    private final Set<Permission> permissionSet;

    Roles(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }
}
