package com.youngg209.controller;

import com.youngg209.dto.ErrorResponseDto;
import com.youngg209.dto.ResultMessageDto;
import com.youngg209.exception.*;
import com.youngg209.utils.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResultMessageDto> processValidationError(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage() ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto("BAD_REQUEST_BODY", message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

    @ExceptionHandler(value = {
            CommonBaseException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleCommonBaseException(CommonBaseException e) {
        String message = e.getMessage() + "[" +e.getVal() + "]" ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto(e.getErrorStatus().getCode(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

}
