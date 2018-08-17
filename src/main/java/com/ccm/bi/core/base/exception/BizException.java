package com.ccm.bi.core.base.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/17 14:08
 * @Description: 业务异常类
 */
public class BizException extends RuntimeException{
    public final String code;

    public BizException(String errorMessage) {
        super(errorMessage);
        this.code = "unknown";
    }

    public BizException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.code = "unknown";
    }

    public BizException(String code, String errorMessage) {
        super(errorMessage);
        this.code = code;
    }

    public BizException(String code, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.code = code;
    }

    public String detailMessage() {
        Throwable cause = this.getCause();
        if (cause == null) {
            cause = this;
        }
        StringWriter stringWriter = new StringWriter();
        cause.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public String errorMessage() {
        return this.getMessage();
    }
}
