package com.aparamonov.cardvalidator.controllers;

import com.aparamonov.cardvalidator.exceptions.CardNumberLengthNotValid;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller to catch and log exceptions.
 *
 * Created by AVParamonov on 17.05.17.
 */
@ControllerAdvice
@Log4j
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleException(CardNumberLengthNotValid exception) {
        log.warn("Card number is not valid: " + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleException(MethodArgumentNotValidException exception) {
        log.warn("Not valid incoming data: " + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handleException(HttpMessageNotReadableException exception) {
        log.warn("Wrong request data", exception);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handleException(HttpMediaTypeNotSupportedException exception) {
        log.warn("Wrong request content-type", exception);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception exception) {
        log.error("Unknown error", exception);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void handleException(SecurityException exception) {
        log.warn("Security error", exception);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void handleException(UsernameNotFoundException exception) {
        log.warn("Unauthorized", exception);
    }
}
