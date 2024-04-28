package com.SecurityGuy.Security.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {

    BUYER(Arrays.asList(Permission.READ_PRODUCTS)),

    SELLER(Arrays.asList(Permission.READ_PRODUCTS, Permission.EDIT_PRODUCT));

    private List<Permission> permissions;

    // Auto generated Constructors
    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    // Getters
    public List<Permission> getPermissions() {
        return permissions;
    }
    // Setters
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
// An enum is a special "class" that represents a group of constants
// (unchangeable variables, like final variables).
// Can be put in a class as an "attribute" or can be declared as a class like here.
