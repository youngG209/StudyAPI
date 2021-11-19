package com.youngg209.exception;

import lombok.Getter;

@Getter
public class NotMatchException extends RuntimeException {

    private Object MatchVal;

    public NotMatchException(Object MatchVal) {
        this.MatchVal = MatchVal;
    }

}
