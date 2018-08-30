package com.zft.oklib.interceptor;


import android.support.annotation.Nullable;

import com.zft.oklib.HttpTestManager;
import com.zft.oklib.log.FLog;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * 作者:zft
 * 日期:2018/5/16 0016.
 */
public class FTestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        FLog.json("FtTestInterceptor拦截器");
        Request request = chain.request();
        String url = request.url().toString();
        String msg = "";
        if ((msg = HttpTestManager.testInterceptor(url)) != null) {
            Response.Builder builder = new Response.Builder();
            builder.request(request);
            builder.protocol(Protocol.HTTP_1_1);
            builder.code(200);
            builder.body(new MyResponseBody(msg));
            builder.message("无网络测试接口");
            return builder.build();
        }
        Response response = chain.proceed(request);
        return response;
    }

    private class MyResponseBody extends ResponseBody {

        private String content;

        public MyResponseBody(String content) {
            this.content = content;
        }

        @Nullable
        @Override
        public MediaType contentType() {
            return MediaType.parse("application/json");
        }

        @Override
        public long contentLength() {
            return content.getBytes().length;
        }

        @Override
        public BufferedSource source() {
            byte[] bytes = content.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Source source = Okio.source(byteArrayInputStream);
            BufferedSource buffer = Okio.buffer(source);
            return buffer;
        }
    }
}
