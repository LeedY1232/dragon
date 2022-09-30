package com.dragon.server.exception;

/**
 * @author henry
 * @date 2022/9/29 17:54
 */
public class DataBaseException extends RuntimeException {
    public DataBaseException(){
        super();
    }
    public DataBaseException(String message) {
        super(message);
    }
}
