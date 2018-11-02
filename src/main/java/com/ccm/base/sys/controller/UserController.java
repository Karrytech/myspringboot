package com.ccm.base.sys.controller;

import com.ccm.base.sys.po.User;
import com.ccm.base.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 17:16
 * @Description:
 */
@Controller
public class UserController implements Controller {

    @Resource
    UserTest userTest;

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

    @GetMapping("/usertest")
    @ResponseBody
    public String testuser(){

        userTest.usertest();
        return "nihao";
    }

    /**
     * 测试
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow(){
//        userTest.usertest();
    }
    @Autowired
    IUserService userService;
    /**
     * 测试
     */
    @Scheduled(cron = "10 0/1 * * * ?")
    public void timerToNow1(){
//        go();
    }

    /**
     * service抛出异常，回滚以后，程序停了，没法重启
     * 只能用中间表策略
     */
    @GetMapping("/testloop")
    public void go() {
        boolean isSuccess = true;
        int count = 0;
        do{
            User user = new User();
            user.setName("hello");
            user.setAge(13);
            try{
                isSuccess = userService.saveUser5(user);
            }catch (Exception e){
                isSuccess = false;
            }
            count ++;
            System.out.println(count);
            System.out.println(isSuccess);
        }while (!isSuccess && count <5);
    }
}
