package com.dragon.server.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dragon.server.config.ApiResponse;
import com.dragon.server.exception.DataBaseException;
import com.dragon.server.exception.ParameterException;

/**
 * @author henry
 * @date 2022/9/29 17:28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ParameterException.class)
    public ApiResponse handleParamException(ParameterException e){
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(value = DataBaseException.class)
    public ApiResponse handleDataBaseException(DataBaseException e){
        return ApiResponse.error(e.getMessage());
    }
}
