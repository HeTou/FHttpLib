package com.zft.oklib.req.cons;

/**
 * 作者:zft
 * 日期:2018/8/29 0029.
 */
public enum FBodyType {
    FORM_DATA(0),X_WWW_FORM_URLENCODED(1),RAW(2),BINARY(3);

    private int type;
    FBodyType(int type) {
        this.type =type;
    }
    public int type(){
        return type;
    }
}

