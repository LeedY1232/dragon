package com.dragon.common.base.transport;

import com.dragon.common.base.transport.ResponseCode;
/**
 * @author Li Dongyang
 * @date 2022/9/20 23:35
 */
public class BaseApiResponse<T> {
    private int rescode;
    private String message;
    private T data;

    public static BaseApiResponse generateResponseByCode(ResponseCode code) {
        return BaseApiResponse.Builder.aBaseApiResponse()
                .setRescode(code.getCode())
                .setMessage(code.getMsg())
                .build();
    }

    public static <T> BaseApiResponse generateResponseWithData(ResponseCode code, T data) {
        return BaseApiResponse.Builder.aBaseApiResponse()
                .setData(data)
                .setMessage(code.getMsg())
                .setRescode(code.getCode())
                .build();
    }

    public int getRescode() {
        return rescode;
    }

    public void setRescode(int rescode) {
        this.rescode = rescode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final class Builder<T> {
        private int rescode;
        private String message;
        private T data;

        private Builder() {
        }

        public static <T> Builder<T> aBaseApiResponse() {
            return new Builder<T>();
        }

        public Builder<T> setRescode(int rescode) {
            this.rescode = rescode;
            return this;
        }

        public Builder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> setData(T data) {
            this.data = data;
            return this;
        }

        public BaseApiResponse<T> build() {
            BaseApiResponse<T> response = new BaseApiResponse<T>();
            response.setData(data);
            response.setRescode(rescode);
            response.setMessage(message);
            return response;
        }
    }
}
