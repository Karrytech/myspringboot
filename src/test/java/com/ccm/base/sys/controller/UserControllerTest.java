package com.ccm.base.sys.controller;

import com.ccm.base.sys.po.User;
import com.ccm.base.sys.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

public class UserControllerTest {
    @Resource
    IUserService userService;

//    @Test
    /*public void testuser() {
        return;
    }*/

    @GetMapping("/usertest")
    @ResponseBody
    public String testuser(){
        User user = new User();
        user.setName("user1");
        user.setAge(12);
        userService.saveUser1(user);
        userService.saveUser2(user);
        userService.saveUser3(user);

        return "nihao";
    }
}