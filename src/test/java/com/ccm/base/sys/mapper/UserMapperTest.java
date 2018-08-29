package com.ccm.base.sys.mapper;

import com.ccm.base.sys.po.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/24 13:07
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
//    @Rollback
    public void findByName() {
//        userMapper.insert("AAA", 20);
        User u = userMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
        System.out.println(u.toString());
        User u2 = userMapper.findByName("AAA");
        Assert.assertEquals(20, u2.getAge().intValue());
        System.out.println(u2.toString());
    }

    @Test
    public void insert() {
        userMapper.insert("CCC", 20);
    }
}