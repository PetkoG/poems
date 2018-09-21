package com.example.poem.web.exception;

public class PoemIdMismatchException extends RuntimeException {

    public PoemIdMismatchException() {
        super();
    }

    public PoemIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PoemIdMismatchException(final String message) {
        super(message);
    }

    public PoemIdMismatchException(final Throwable cause) {
        super(cause);
    }

}
