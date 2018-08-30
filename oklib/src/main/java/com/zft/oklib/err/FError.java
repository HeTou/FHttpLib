package com.zft.oklib.err;

/**
 * zft
 * 2017/4/21.
 */

public class FError extends Exception {
    public FError(){}

    public FError(String detailMessage) {
        super(detailMessage);
    }

    public FError(Throwable throwable) {
        super(throwable);
    }
}
