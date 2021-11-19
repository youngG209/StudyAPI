package com.youngg209.utils;

import lombok.Getter;

@Getter
public enum SchoolType {
    ELE("ELEMENTARY"),
    MID("MIDDLE"),
    HIGH("HIGH")
    ;

    private final String code;

    SchoolType(String code) {
        this.code = code;
    }
}
