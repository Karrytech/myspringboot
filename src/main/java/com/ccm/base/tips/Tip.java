package com.ccm.base.tips;


/**
 * @Auther: Cassidy dev
 * @Date: 2018/8/12 22:05
 * @Description: (* _ *)
 */
public abstract class Tip {

    protected int code;
    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
