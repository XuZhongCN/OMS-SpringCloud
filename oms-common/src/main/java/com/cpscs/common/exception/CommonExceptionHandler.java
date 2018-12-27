package com.cpscs.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cpscs.common.utils.R;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    R exception(Exception e) {
        return R.error(500, e.getMessage());
    }
}
