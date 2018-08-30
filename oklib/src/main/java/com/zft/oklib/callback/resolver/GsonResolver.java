package com.zft.oklib.callback.resolver;//package com.tentcoo.baseframework.common.http.okhttp.callback.resolver;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
///**
// * Created by Administrator on 2017/9/27 0027.
// */
//
//public class GsonResolver implements FtResolver {
//    @Override
//    public <T> T transform(String response, Class<T> classOfT) throws Exception {
//        Gson gson = new GsonBuilder().create();
//        T t = gson.fromJson(response, classOfT);
//        return t;
//    }
//}
