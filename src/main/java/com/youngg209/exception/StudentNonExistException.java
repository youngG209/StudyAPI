package com.youngg209.exception;

import lombok.Getter;

@Getter
public class StudentNonExistException extends RuntimeException {

    private Long id;

    public StudentNonExistException(Long id) {
        this.id = id;
    }
}
