package com.ccm.base.utils;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/15 16:17
 * @Description:
 */
public class RespVOUtil {

    public static RespVO ok(Object obj){
        return new RespVO<>(RespEnum.OK.getCode(), RespEnum.OK.getMessage(),obj);
    }

    public static RespVO ok(){
        return RespVOUtil.ok(null);
    }

    public static RespVO err(Integer code,String msg){
        return new RespVO<>(code,msg,null);
    }

    public static RespVO err(RespEnum respEnum){
        return err(respEnum.getCode(),respEnum.getMessage());
    }
}
