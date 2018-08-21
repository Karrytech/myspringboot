package com.ccm.base.sys.dao;

import com.ccm.base.sys.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 11:15
 * @Description:
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
