package com.ccm.base.sys.service;

import com.ccm.base.sys.po.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {

    @Transactional
    void saveUser1(User user);

    @Transactional
    void saveUser2(User user);

    @Transactional
    void saveUser3(User user);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveUser4(User user);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    boolean saveUser5(User user);
}
