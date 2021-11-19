package com.youngg209.exception;

import lombok.Getter;

@Getter
public class StudentExistException extends RuntimeException {

    private String phoneNumber;

    public StudentExistException(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
