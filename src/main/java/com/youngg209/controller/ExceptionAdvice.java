package com.youngg209.controller;

import com.youngg209.dto.ErrorResponseDto;
import com.youngg209.dto.ResultMessageDto;
import com.youngg209.exception.*;
import com.youngg209.utils.ApiStatus;
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
            StudentExistException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleStudentExistException(StudentExistException e) {
        final ApiStatus apiStatus = ApiStatus.AESTU;
        String message = apiStatus.getMessage() + "[" +e.getPhoneNumber() + "]" ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto(apiStatus.getCode(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

    @ExceptionHandler(value = {
            SubjectExistException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleSubjectExistException(SubjectExistException e) {
        final ApiStatus apiStatus = ApiStatus.AESUB;
        String message = apiStatus.getMessage() + "[" +e.getName() + "]" ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto(apiStatus.getCode(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

    @ExceptionHandler(value = {
            StudentNonExistException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleStudentNonExistException(StudentNonExistException e) {
        final ApiStatus apiStatus = ApiStatus.STUNF;
        String message = apiStatus.getMessage() + "[" +e.getId() + "]" ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto(apiStatus.getCode(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

    @ExceptionHandler(value = {
            SubjectNonExistException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleSubjectNonExistException(SubjectNonExistException e) {
        final ApiStatus apiStatus = ApiStatus.SUBNF;
        String message = apiStatus.getMessage() + "[" +e.getId() + "]" ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto(apiStatus.getCode(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

    @ExceptionHandler(value = {
            NotMatchException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResultMessageDto> handleNotMatchException(NotMatchException e) {
        final ApiStatus apiStatus = ApiStatus.NOT_MATCH_VAL;
        String message = apiStatus.getMessage() + "[" +e.getMatchVal() + "]" ;
        log.error(message);
        ErrorResponseDto error = new ErrorResponseDto(apiStatus.getCode(), message);
        return ResponseEntity.badRequest().body(new ResultMessageDto(null, error));
    }

}
