package com.zft.oklib.callback.resolver;

import com.alibaba.fastjson.JSON;

/**
 * author:zft
 * date:2017/11/22 0022.
 */

public class FastResolver implements FtResolver {
    private static FastResolver instance;

    public static FastResolver getInstance() {
        if (instance == null) {
            instance = new FastResolver();
        }
        return instance;
    }

    @Override
    public <T> T transform(String response, Class<T> classOfT) throws Exception {
        T t = JSON.parseObject(response, classOfT);
        return t;
    }
}
