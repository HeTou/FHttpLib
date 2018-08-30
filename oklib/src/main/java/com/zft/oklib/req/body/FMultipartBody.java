package com.zft.oklib.req.body;


import com.zft.oklib.req.cons.FBodyType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public class FMultipartBody extends FBody {
    private Map<String, Object> params = new HashMap<>();
    private String contentType;

    public FMultipartBody(Map<String, Object> params, String contentType) {
        this.params = params;
        this.contentType = contentType;
    }

    @Override
    public FBodyType bodyType() {
        return FBodyType.FORM_DATA;
    }

    @Override
    public RequestBody body() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        MediaType mediaType = null;

        if (params == null) {
            throw new IllegalArgumentException("the form can not be null !");
        }
        if (contentType == null) {
            contentType = "image/*";
        }
        mediaType = MediaType.parse(contentType);
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            Object obj = params.get(key);
            if (obj instanceof File) {
                File file = (File) obj;
                builder.addFormDataPart(key, file.getName(), RequestBody.create(mediaType, file));
            } else if (obj instanceof String) {
                builder.addFormDataPart(key, (String) obj);
            }
        }
        return builder.build();
    }
}
