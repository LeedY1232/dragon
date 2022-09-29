package com.dragon.server.exception;

/**
 * @author henry
 * @date 2022/9/29 17:30
 */
public class ParameterException extends RuntimeException {

    public ParameterException() {
        super();
    }

    public ParameterException(String message) {
        super(message);
    }
}
