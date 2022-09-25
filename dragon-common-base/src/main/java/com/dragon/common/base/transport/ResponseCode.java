package com.dragon.common.base.transport;

import java.util.HashMap;
import java.util.Map;


public enum ResponseCode {

    // success
    SUCCESS(0, "success"),

    // common error
    SYS_BUSY(-1, "system internal busy"),
    BAD_REQ_PARAM(-2, "bad request params"),
    RESOURCE_NOT_FOUND(-4, "resource not found"),
    FORBIDDEN(-5, "forbidden"),
    BAD_URI(-6, "wrong uri"),
    BAD_PARAM_FORMAT(-7, "wrong param format"),
    BAD_HTTP_METHOD(-8, "wrong http method"),
    UNCERTAIN(-10, "result is uncertain"),

    // auth & permission
    AUTH_TOKEN_FAIL(10001, "auth token checking failed"),
    AUTH_TOKEN_MISSING(10002, "missing auth token"),
    ACCOUNT_CANCELED(10004, "user account has been canceled"),
    NO_AUTH(10016, "request has been denied"),
    ;

    private static final Map<Integer, ResponseCode> resCodeMap = new HashMap<>();

    static {
        for (ResponseCode resCode : ResponseCode.values()) {
            resCodeMap.put(resCode.code, resCode);
        }
    }

    private final int code;

    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseCode valueOf(Integer code) {
        return code != null ? resCodeMap.get(code) : null;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
