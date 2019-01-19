package com.zft.oklib.req;

import com.zft.oklib.FHttp;
import com.zft.oklib.callback.IFCallBack;
import com.zft.oklib.callback.util.FPlatform;
import com.zft.oklib.err.FNetworkError;
import com.zft.oklib.err.FRequestCancelError;
import com.zft.oklib.err.FServerError;
import com.zft.oklib.log.FLog;
import com.zft.oklib.req.body.FBinaryBody;
import com.zft.oklib.req.body.FBody;
import com.zft.oklib.req.body.FFormBody;
import com.zft.oklib.req.body.FMultipartBody;
import com.zft.oklib.req.body.FRawBody;
import com.zft.oklib.req.cons.FBodyType;
import com.zft.oklib.req.cons.FMethod;
import com.zft.oklib.req.header.FHeader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 * 网络请求操作者
 */
public class FPostman {

    private static FPlatform platform = FPlatform.get();

    public static FPlatform getPlatform() {
        return platform;
    }

    public static void asyn(FRequest fRequest, IFCallBack callBack) {
        Call call = buildCall(fRequest);
        callBack.onBefore();
        call.enqueue(new OkSimpleCallBack(callBack));
    }


    public static void syn(FRequest fRequest, IFCallBack callBack) {
        Call call = buildCall(fRequest);
        Response response = null;
        try {
            callBack.onBefore();
            response = call.execute();
            if (call.isCanceled()) {
                requestFail(call, new FRequestCancelError(fRequest.getUrl() + "-reqeust Canceled!"), callBack);
            }
            if (!callBack.validateReponse(response)) {
                requestFail(call, new FServerError("request failed , reponse's code is : " + response.code()), callBack);
                return;
            }
            Object o = callBack.parseNetworkResponse(response);
            requestSuccess(o, callBack);
        } catch (Exception e) {
            requestFail(call, e, callBack);
        }
    }


    /***
     * 获取okhttp的请求对象
     * @param fRequest
     * @return
     */
    private static Call buildCall(FRequest fRequest) {
        String method = fRequest.getMethod();
        String url = fRequest.getUrl();
        Object tag = fRequest.getTag();
        FBody body = fRequest.getBody();
        FHeader header = fRequest.getHeader();

        Request.Builder builder = new Request.Builder();
        RequestBody requestBody = null;
        if (FMethod.GET.getMethod().equalsIgnoreCase(method) || FMethod.HEAD.getMethod().equalsIgnoreCase(method)) {

        } else {
            if (body != null) {
                if (body.bodyType() == FBodyType.X_WWW_FORM_URLENCODED) {
                    //普通的參數上傳
                    FFormBody formBody = (FFormBody) body;
                    requestBody = formBody.body();
                } else if (body.bodyType() == FBodyType.FORM_DATA) {
                    //表单上传
                    FMultipartBody multipartBody = (FMultipartBody) body;
                    requestBody = multipartBody.body();
                } else if (body.bodyType() == FBodyType.RAW) {
                    // 字符串上传
                    FRawBody rawBody = (FRawBody) body;
                    requestBody = rawBody.body();
                } else if (body.bodyType() == FBodyType.BINARY) {
                    //二进制文件上传
                    FBinaryBody binaryBody = (FBinaryBody) body;
                    requestBody = binaryBody.body();
                }
            } else {
                requestBody = new FormBody.Builder().build();
            }
        }
//      添加请求头
        if (header != null) {
            Map<String, String> headers = header.getHeaders();
            if (headers != null && headers.size() > 0) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    builder.addHeader(key, headers.get(key));
                }
            }
        }
        Request req = builder.method(method, requestBody)
                .url(url)
                .tag(tag)
                .build();
        Call call = FHttp.getInstance().getHttpClient().newCall(req);
        return call;
    }

    public static class OkSimpleCallBack implements Callback {
        private IFCallBack callBack;

        public OkSimpleCallBack(IFCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
            requestFail(call, new FNetworkError( e), callBack);
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (call.isCanceled()) {
                    requestFail(call, new FRequestCancelError(response.request().url() + "-reqeust Canceled!"), callBack);
                    return;
                }
                if (!callBack.validateReponse(response)) {
                    requestFail(call, new FServerError("request failed , reponse's code is : " + response.code()), callBack);
                    return;
                }
                Object o = callBack.parseNetworkResponse(response);
                requestSuccess(o, callBack);
            } catch (Exception e) {
                requestFail(call, e, callBack);
            }
        }
    }

    /***
     * 请求成功
     * @param myCallBack
     */
    private static void requestSuccess(final Object o, final IFCallBack myCallBack) {
        platform.execute(new Runnable() {
            @Override
            public void run() {
                myCallBack.onResponse(o);
                myCallBack.onAfter();
            }
        });
    }


    /***
     * 请求失败
     * @param call
     * @param e
     * @param myCallBack
     */
    private static void requestFail(final Call call, final Exception e, final IFCallBack myCallBack) {
//        e.printStackTrace();
        platform.execute(new Runnable() {
            @Override
            public void run() {
                FLog.json(e.getMessage());
                myCallBack.onError(call, e);
                myCallBack.onAfter();
            }
        });
    }


}
