package com.ccm.base.sys.dao;

import com.ccm.base.sys.po.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 11:16
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setName("a user");
        user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        userDao.save(user);
    }

    @Test
    public void updateUserTest(){
        User user = userDao.findById(10).orElse(null);

        Assert.assertNotNull(user);

        user.setName("what`s my name--");

        userDao.save(user);
    }

    @Test
    public void findUser(){
        User user = userDao.findById(2).orElse(null);
        System.out.println(user.toString());
    }
}