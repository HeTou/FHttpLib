package com.zft.oklib.req.body;


import com.zft.oklib.req.cons.FBodyType;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public class FRawBody extends FBody {
    private String content;
    private String contentType;

    public FRawBody(String content) {
        this.content = content;
        this.contentType = "text/plain;charset=utf-8";
    }

    public FRawBody(String content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    @Override
    public FBodyType bodyType() {
        return FBodyType.RAW;
    }

    @Override
    public RequestBody body() {
        RequestBody requestBody = null;
        MediaType mediaType = null;

        if (content == null) {
            throw new IllegalArgumentException("the content can not be null !");
        }
        if (contentType == null) {
            contentType = "text/plain;charset=utf-8";
        }
        mediaType = MediaType.parse(contentType);
        requestBody = RequestBody.create(mediaType, content);
        return requestBody;
    }
}
