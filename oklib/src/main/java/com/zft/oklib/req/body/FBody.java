package com.zft.oklib.req.body;


import com.zft.oklib.req.cons.FBodyType;

import okhttp3.RequestBody;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 * 请求体
 */
public abstract class FBody {
    public abstract FBodyType bodyType();

    public abstract RequestBody body();
}
