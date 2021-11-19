package com.youngg209.exception;

import lombok.Getter;

@Getter
public class SubjectExistException extends RuntimeException {

    private String name;

    public SubjectExistException(String name) {
        this.name = name;
    }
}
