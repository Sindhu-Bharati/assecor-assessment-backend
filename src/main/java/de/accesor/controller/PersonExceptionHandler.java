package de.accesor.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.accesor.exceptions.CsvFileException;
import de.accesor.exceptions.PersonColourMappingException;
import de.accesor.exceptions.UserNotFoundException;

@ControllerAdvice(assignableTypes = PersonController.class)
public class PersonExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PersonExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseErrorDto> handleException(final Exception exception, final HttpServletRequest httpRequest) {
        LOG.error("An exception occurred", exception);
    	final ResponseErrorDto error = new ResponseErrorDto(exception.getMessage(), 500);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(value = CsvFileException.class)
    public ResponseEntity<ResponseErrorDto> handleCsvFileException(final Exception exception, final HttpServletRequest httpRequest) {
        LOG.error("An exception occurred while parsing csv file", exception);
        final ResponseErrorDto error = new ResponseErrorDto(exception.getMessage(), 422);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(value = PersonColourMappingException.class)
    public ResponseEntity<ResponseErrorDto> handleColorMappingException(final Exception exception, final HttpServletRequest httpRequest) {
        LOG.error("An exception occurred while mapping person colour", exception);
        final ResponseErrorDto error = new ResponseErrorDto(exception.getMessage(), 422);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> userNotFound(final Exception exception, final HttpServletRequest httpRequest) {
        LOG.error("An exception occurred", exception);
        final ResponseErrorDto error = new ResponseErrorDto(exception.getMessage(), 404);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
