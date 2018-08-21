package com.ccm.base.sys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Annotation;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 17:16
 * @Description:
 */
@Controller
public class UserController implements Controller {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public String test(){
        return "";
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
