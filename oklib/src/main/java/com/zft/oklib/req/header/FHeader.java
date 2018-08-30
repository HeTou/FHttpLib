package com.zft.oklib.req.header;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 * 请求头
 */
public class FHeader {
    private Map<String, String> headers = new HashMap<>();

    public FHeader() {
    }

    public FHeader(Map<String, String> headers) {
        this.headers = headers;
    }


    /***
     * 设置请求头
     * @param headers
     * @return
     */
    public FHeader setHeaders(Map<String, String> headers) {
        headers.clear();
        headers.putAll(headers);
        return this;
    }

    /***
     * 添加请求头
     * @param key 注意这里的
     * @param value
     * @return
     */
    public FHeader addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
