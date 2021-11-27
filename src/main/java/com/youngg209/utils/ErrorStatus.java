package com.youngg209.utils;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    AESTU("ALREADY_EXIST_STUDENT", "이미 존재하는 학생입니다. ", 400),
    AESUB("ALREADY_EXIST_SUBJECT", "이미 존재하는 과목입니다. ", 400),
    STUNF("STUDENT_NOT_FOUND", "학생을 찾을 수 없습니다. ", 400),
    SUBNF("SUBJECT_NOT_FOUND", "과목을 찾을 수 없습니다. ", 400),
    NOT_MATCH_VAL("NOT_MATCH_VAL", "입력된 값이 조건에 맞지 않습니다. ", 400),
    CHECK_SCORE("CHECK_SCORE", "점수는 0~100까지만 가능합니다. ", 400)
    ;

    private final String code;
    private final String message;
    private final int status;

    ErrorStatus(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
