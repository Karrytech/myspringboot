package com.ccm.base.sys.dao;

import com.ccm.base.sys.po.DtmjUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 10:00
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DtmjUserDaoTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DtmjUserDao dtmjUserDao;

    @Test
    public void findAllTest(){
        DtmjUser user = dtmjUserDao.findAll().get(0);
        log.info(user.toString());
    }

    @Test
    public void saveTest(){
        DtmjUser user = new DtmjUser();
        user.setCardNo("hahah");
        user.setIdNo("asdf");
        user.setPersonName("我是第一个user");
        dtmjUserDao.save(user);

    }


    @Test
    public void updateTest(){
        DtmjUser user = new DtmjUser();
        user.setCardNo("hahah");
        user.setIdNo("asdf");
        user.setPersonName("我是第一个user");

    }

}