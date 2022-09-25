package com.dragon.common.base.exception;

/**
 * @author Li Dongyang
 * @date 2022/9/25 18:34
 */
public class ParameterException extends RuntimeException{
    public ParameterException(){
        super();
    }

    public ParameterException(String errMsg){
        super(errMsg);
    }
}
