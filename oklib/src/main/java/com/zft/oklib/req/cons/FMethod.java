package com.zft.oklib.req.cons;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public enum FMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
//    OPTIONS("OPTIONS"),
//    TRACE("TRACE"),
    PATCH("PATCH"),;

    private String method;

    FMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
