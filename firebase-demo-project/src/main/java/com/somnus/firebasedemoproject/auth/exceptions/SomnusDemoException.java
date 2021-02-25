package com.somnus.firebasedemoproject.auth.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomnusDemoException extends RuntimeException {
    private static final Logger somnusLogger = LoggerFactory.getLogger(SomnusDemoException.class);

    public SomnusDemoException(String message) {
        super(message);
        somnusLogger.error(message);
    }

}