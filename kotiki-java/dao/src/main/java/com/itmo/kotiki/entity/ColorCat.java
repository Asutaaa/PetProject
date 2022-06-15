package com.itmo.kotiki.entity;

public enum ColorCat {
    WHITE("WHITE"),
    GREY("GREY"),
    BLACK("BLACK"),
    ORANGE("ORANGE");
    private String num;

    ColorCat(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }
}