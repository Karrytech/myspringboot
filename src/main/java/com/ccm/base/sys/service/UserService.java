package com.ccm.base.sys.service;

import com.ccm.base.sys.po.User;
import com.ccm.base.sys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用user的插入测试spring事务
 */
@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser1(User user) {
        userRepository.save(user);
//        throw new RuntimeException("hia ,user1 报错");
    }

    /**
     * 开启新的事务，不抛出异常
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser2(User user) {
        try {
            user.setName("user2");
            userRepository.save(user);
            throw new RuntimeException("hia ,user2 报错");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 不支持事务，但是会抛出运行时异常
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveUser3(User user) {
        try {
            user.setName("user3");
            userRepository.save(user);
            throw new RuntimeException("hia ,user3 报错");
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("hia ,user3 报错");
        }
    }

    /**
     * 开启新的事务，抛出异常
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser4(User user) {
        user.setName("user4");
        userRepository.save(user);
        throw new RuntimeException("hia ,user4 报错");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveUser5(User user) {
        user.setName("user5");
        userRepository.save(user);
        double aa = Math.random();
        System.out.println("-----------------------------------------------------------------------------------"+aa);
        if (aa < 0.5) {
            throw new RuntimeException("hia ,user5 报错");
        }
        return true;
    }
}
