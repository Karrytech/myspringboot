package com.ccm.base.sys.controller;

import com.ccm.base.sys.po.User;
import com.ccm.base.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserTest {

    @Autowired
    IUserService userService;

    @Transactional
    public void usertest(){

        User user = new User();
        user.setName("user1");
        user.setAge(12);
        userService.saveUser1(user);
        userService.saveUser2(user);
        userService.saveUser3(user);
        userService.saveUser4(user);
    }
}
