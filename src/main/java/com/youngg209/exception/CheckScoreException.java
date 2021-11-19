package com.youngg209.exception;

import lombok.Getter;

@Getter
public class CheckScoreException extends RuntimeException {

    private int score;

    public CheckScoreException(int score) {
        this.score = score;
    }
}
