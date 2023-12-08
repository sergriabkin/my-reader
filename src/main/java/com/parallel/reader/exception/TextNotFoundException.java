package com.parallel.reader.exception;

public class TextNotFoundException extends RuntimeException {

    private static final String TEXT_NOT_FOUND_MESSAGE = "Text with id %d not found";

    public TextNotFoundException(long id) {
        super(String.format(TEXT_NOT_FOUND_MESSAGE, id));
    }
}
