package de.accesor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.accesor.exceptions.UserNotFoundException;
import de.accesor.models.ResponseErrorDto;

@ControllerAdvice(assignableTypes = PersonController.class)
public class PersonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseErrorDto> handleException(final Exception exception, final HttpServletRequest httpRequest) {
        System.out.println(exception);
    	final ResponseErrorDto error = new ResponseErrorDto(exception.getMessage(), 500);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> userNotFound(final Exception exception, final HttpServletRequest httpRequest) {
        final ResponseErrorDto error = new ResponseErrorDto(exception.getMessage(), 404);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
