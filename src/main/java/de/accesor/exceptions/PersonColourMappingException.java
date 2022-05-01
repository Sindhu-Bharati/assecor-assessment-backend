package de.accesor.exceptions;

public class PersonColourMappingException extends RuntimeException {

    public PersonColourMappingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PersonColourMappingException(final String message) {
        super(message);
    }
}
