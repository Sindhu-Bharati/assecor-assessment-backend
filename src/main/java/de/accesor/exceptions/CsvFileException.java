package de.accesor.exceptions;

public class CsvFileException extends RuntimeException {
    public CsvFileException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CsvFileException(final String message) {
        super(message);
    }
}