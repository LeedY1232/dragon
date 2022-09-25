package com.dragon.common.base.transport;


public class HttpProtocol {

    /**
     * HTTP 请求头
     */
    public static class RequestHeader {
        /**
         * 代理
         */
        public static final String X_FORWARDED_FOR = "X-Forwarded-For";
        public static final String X_REAL_IP = "X-Real-IP";
        public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
        public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
        public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";


        public static final String OPERATION_ID = "X-Operation-ID";

        public static final String MEMBER_ID = "auth-jn";
        public static final String MEMBER_AUTH_TOKEN = "auth-token";
        public static final String MEMBER_PERMISSIONS = "permissions";

        public static final String REFERER = "referer";
        public static final String USER_AGENT = "User-Agent";

        public static final String REQUEST_ID = "Request-Id";
        public static final String REQUEST_RANDOM = "Request-Random";
        public static final String REQUEST_SIGN = "Request-Sign";
    }

    /**
     * HTTP 响应头
     */
    public static class ResponseHeader {

        public static final String RESPONSE_CODE = "X-Rescode";
    }

    /**
     * 请求属性
     */
    public static class RequestAttribute {

        public static final String OPERATION_ID = "operationId";
    }

    /**
     * 响应头 Content-Type
     */
    public static class ContentType {

        public static final String JSON_UTF8 = "application/json; charset=utf-8";
    }

    /**
     * Cookie
     */
    public static class Cookie {

        public static final String HANJU_ADMIN_AUTH = "HJAD_AUTH";
        public static final String BBS_ADMIN_AUTH = "BBS_AUTH";
    }
}
