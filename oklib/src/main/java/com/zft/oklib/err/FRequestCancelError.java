package com.zft.oklib.err;

/**
 * zft
 * 2017/4/21.
 * 任务取消
 */

public class FRequestCancelError extends FError {
    public FRequestCancelError(){}
    public FRequestCancelError(Throwable throwable) {}
    public FRequestCancelError(String detailMessage) {
        super(detailMessage);
    }
}
