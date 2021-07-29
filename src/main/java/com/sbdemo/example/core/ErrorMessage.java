package com.sbdemo.example.core;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 *
 * @author leo
 */
@Getter
@ToString
public class ErrorMessage {

    private final String error;
    private final String message;
    private final Integer code;

    public ErrorMessage(Exception exception, HttpStatus httpStatus) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.code = httpStatus.value();
    }

}
