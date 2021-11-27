package com.youngg209.exception;

import com.youngg209.utils.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommonBaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorStatus errorStatus;
    private String val;

    public CommonBaseException(ErrorStatus errorStatus, String val) {
        this.errorStatus = errorStatus;
        this.val = val;
    }
}
