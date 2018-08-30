package com.zft.oklib.err;

/**
 * zft
 * 2017/4/21.
 * 网络异常
 */

public class FNetworkError extends FError {
    public FNetworkError(){}

    public FNetworkError(Throwable throwable) { }

    public FNetworkError(String detailMessage) {
        super(detailMessage);
    }
}
