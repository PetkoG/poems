package com.example.poem.web.exception;

public class PoemNotFoundException extends RuntimeException {

    public PoemNotFoundException() {
        super();
    }

    public PoemNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PoemNotFoundException(final String message) {
        super(message);
    }

    public PoemNotFoundException(final Throwable cause) {
        super(cause);
    }

}
