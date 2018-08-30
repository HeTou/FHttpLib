package com.zft.oklib.callback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * zft
 * 2017/4/21.
 */

public abstract class IFCallBack<T> {

    public void onBefore() {
    }


    public void onAfter() {
    }


    public void inProgress(float progress, long total) {

    }


    public boolean validateReponse(Response response) {
        return response.isSuccessful();
    }

    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);

    public static IFCallBack DEFAULT_CALLBACK = new IFCallBack() {
        @Override
        public Object parseNetworkResponse(Response response) throws Exception {
            return null;
        }


        @Override
        public void onError(Call call, Exception e) {

        }

        @Override
        public void onResponse(Object response) {

        }
    };

}
