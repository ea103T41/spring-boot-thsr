package com.euphy.learn.dto;

public enum FareType {

    STANDARD("標準車廂"),
    BUSINESS("商務車廂"),
    NON_RESERVED("自由座車廂");

    private final String fareTypeName;

    // 构造函数是 private 的
    FareType(String fareTypeName) {
        this.fareTypeName = fareTypeName;
    }

    // 提供 getter 方法，不提供 setter 方法
    public String getFareTypeName() {
        return fareTypeName;
    }
}
