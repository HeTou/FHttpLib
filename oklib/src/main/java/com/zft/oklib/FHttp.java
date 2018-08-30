package com.zft.oklib;


import com.zft.oklib.req.cons.FMethod;
import com.zft.oklib.interceptor.FLogInterceptor;
import com.zft.oklib.req.FRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 * 网络请求类
 */
public class FHttp {
    private static FHttp instance;
    private OkHttpClient okHttpClient;

    private FHttp() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new FLogInterceptor())
                .connectTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getHttpClient() {
        return okHttpClient;
    }

    public static FHttp getInstance() {
        if (instance == null) {
            synchronized (FHttp.class) {
                if (instance == null) {
                    instance = new FHttp();
                }
            }
        }
        return instance;
    }

    public static FRequest.Builder get() {
        return method(FMethod.GET);
    }

    public static FRequest.Builder post() {
        return method(FMethod.POST);
    }

    public static FRequest.Builder put() {
        return method(FMethod.PUT);
    }

    public static FRequest.Builder delete() {
        return method(FMethod.DELETE);
    }

    /***
     * 设置请求方式
     * @param method
     * @return
     */
    public static FRequest.Builder method(FMethod method) {
        FRequest.Builder builder = new FRequest.Builder();
        builder.method(method);
        return builder;
    }
}
