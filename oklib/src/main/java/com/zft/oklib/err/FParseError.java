package com.zft.oklib.err;

/**
 * zft
 * 2017/4/21.
 * 解析异常
 */

public class FParseError extends FError {
    public FParseError(){}
    public FParseError(Throwable throwable) {}

    public FParseError(String detailMessage) {
        super(detailMessage);
    }
}
