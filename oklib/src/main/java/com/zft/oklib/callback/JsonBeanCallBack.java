package com.zft.oklib.callback;


import com.zft.oklib.callback.resolver.FastResolver;
import com.zft.oklib.callback.resolver.FtResolver;
import com.zft.oklib.err.FParseError;

import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
 * zft
 * 2017/4/21.
 */

public abstract class JsonBeanCallBack<T> extends IFCallBack<T> {
    private FtResolver resolver;

    public JsonBeanCallBack() {
        this.resolver = FastResolver.getInstance();
    }

    public JsonBeanCallBack(FtResolver resolver) {
        this.resolver = resolver;
        if (this.resolver == null) {
            this.resolver = FastResolver.getInstance();
        }
    }

    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        T bean = null;
        try {
            String body = response.body().string();
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (entityClass == String.class) {
                return (T) body;
            }
            bean = resolver.transform(body, entityClass);
        } catch (Exception e) {
            throw new FParseError(e);
        }
        return bean;
    }
}
