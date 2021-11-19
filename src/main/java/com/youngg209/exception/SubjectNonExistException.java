package com.youngg209.exception;

import lombok.Getter;

@Getter
public class SubjectNonExistException extends RuntimeException {

    private Long id;

    public SubjectNonExistException(Long id) {
        this.id = id;
    }
}
