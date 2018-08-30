package com.zft.oklib.callback.resolver;

/**
 * Created by JimGong on 2016/6/23.
 *
 */
public interface FtResolver {
    <T> T transform(String response, Class<T> classOfT) throws Exception;
}
