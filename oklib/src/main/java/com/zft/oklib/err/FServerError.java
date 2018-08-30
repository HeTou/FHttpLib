package com.zft.oklib.err;

/**
 * zft
 * 2017/4/21.
 * 服务异常
 */

public class FServerError extends FError {
    public FServerError() {
        super();
    }

    public FServerError(String detailMessage) {
        super(detailMessage);
    }

    public FServerError(Throwable throwable) {
        super(throwable);
    }
}
