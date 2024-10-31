package com.sparta.sheduleapp.entity;

public enum UserRoleEnum {
    ADMIN("관리자"),
    USER("유저");

    private final String description;

    UserRoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static UserRoleEnum getRole(boolean isAdmin){
        return isAdmin ? ADMIN : USER;
    }

}