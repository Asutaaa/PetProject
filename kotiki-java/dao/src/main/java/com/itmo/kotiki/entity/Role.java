package com.itmo.kotiki.entity;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    private String num;

    Role(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }
}
