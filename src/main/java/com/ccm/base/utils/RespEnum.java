package com.ccm.base.utils;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/15 17:17
 * @Description: 返回消息状态码
 */
public enum RespEnum {

    OK(0,"操作成功"),

    ERR_PARAM(100,"参数错误"),

    BAD_MOOD_ERR(44,"今天心情不好,报个错");

    private Integer code ;

    private String message;

    RespEnum(Integer code, String message){

        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
