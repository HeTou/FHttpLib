package com.zft.oklib.req.body;


import com.zft.oklib.req.cons.FBodyType;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public class FBinaryBody extends FBody {
    private File file;
    private String contentType;

    public FBinaryBody(File file, String contentType) {
        this.file = file;
        this.contentType = contentType;
    }

    @Override
    public FBodyType bodyType() {
        return FBodyType.BINARY;
    }

    @Override
    public RequestBody body() {
        RequestBody requestBody;
        MediaType mediaType = null;
        if (file == null) {
            throw new IllegalArgumentException("the file can not be null !");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        mediaType = MediaType.parse(contentType);
        requestBody = RequestBody.create(mediaType, file);
        return requestBody;
    }
}
