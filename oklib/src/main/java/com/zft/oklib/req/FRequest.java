package com.zft.oklib.req;


import com.zft.oklib.callback.IFCallBack;
import com.zft.oklib.req.body.FBinaryBody;
import com.zft.oklib.req.body.FBody;
import com.zft.oklib.req.body.FFormBody;
import com.zft.oklib.req.body.FMultipartBody;
import com.zft.oklib.req.body.FRawBody;
import com.zft.oklib.req.cons.FMethod;
import com.zft.oklib.req.header.FHeader;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public class FRequest {
    private String method;  //请求方式
    private String url; //请求地址
    private Object tag; //标签,用于取消请求
    private FHeader header; //请求头
    private FBody body;     //请求体

    public FRequest(String method, String url, Object tag, FHeader header, FBody body) {
        this.url = url;
        this.tag = tag;
        this.header = header;
        this.body = body;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Object getTag() {
        return tag;
    }

    public FHeader getHeader() {
        return header;
    }

    public FBody getBody() {
        return body;
    }

    public void asyn(IFCallBack callBack) {
        FPostman.asyn(this, callBack);
    }

    public void syn(IFCallBack callBack) {
        FPostman.syn(this, callBack);
    }

    public static class Builder {
        private String method;
        private String url;
        private Object tag;
        private FHeader header;
        private FBody body;

        /***
         * 设置请求连接
         * @param url   请求连接
         * @return
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /***
         * 设置请求方式
         * @param method
         * @return
         */
        public Builder method(FMethod method) {
            this.method = method.getMethod();
            return this;
        }

        /***
         * 设置请求标签,主要用于取消请求
         * @param tag   请求标签
         * @return
         */
        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        /***
         * 设置请求头
         * @param headers   请求体数组
         * @return
         */
        public Builder headers(Map<String, String> headers) {
            this.header = new FHeader(headers);
            return this;
        }

        /***
         * 超长文本上传
         * @param content   请求体内容
         * @param mediaType 请求体类型;如：application/json
         * @return
         */
        public Builder body(String content, String mediaType) {
            this.body = new FRawBody(content, mediaType);
            return this;
        }

        public Builder body(String content) {
            return this.body(content, "application/json;charset=UTF-8");
        }

        /***
         * 表單上傳
         * @param params
         * @param mediaType
         * @return
         */
        public Builder body(Map<String, Object> params, String mediaType) {
            this.body = new FMultipartBody(params, mediaType);
            return this;
        }

        /***
         * 二进制流文件上传
         * @param file
         * @param mediaType
         * @return
         */
        public Builder body(File file, String mediaType) {
            this.body = new FBinaryBody(file, mediaType);
            return this;
        }

        /***
         * 设置请求参数
         * @param params
         * @return
         */
        public Builder params(Map<String, String> params) {
            if (FMethod.GET.getMethod().equalsIgnoreCase(method)) {
                this.url = createGetUrl(params);
            } else {
                this.body = new FFormBody(params);
            }
            return this;
        }


        public FRequest builder() {
            return new FRequest(method, url, tag, header, body);
        }


        private String createGetUrl(Map<String, String> params) {
            if (url == null) {
                throw new RuntimeException("请先设置url");
            }
            StringBuffer sb = new StringBuffer();
            sb.append(url);
            if (params != null && params.size() > 0) {
                sb.append("?");
                Set<String> keys = params.keySet();
                for (String key : keys) {
                    String value = params.get(key);
                    sb.append(key).append("=").append(value).append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }
}
