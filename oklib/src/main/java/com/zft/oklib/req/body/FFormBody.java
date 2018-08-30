package com.zft.oklib.req.body;


import com.zft.oklib.req.cons.FBodyType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public class FFormBody extends FBody {
    private Map<String, String> params = new HashMap<>();

    public FFormBody(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public FBodyType bodyType() {
        return FBodyType.X_WWW_FORM_URLENCODED;
    }

    @Override
    public RequestBody body() {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                bodyBuilder.add(key, params.get(key));
            }
        }
        return bodyBuilder.build();
    }
}
