package com.ccm.base.sys.repository;

import com.ccm.base.sys.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Cacheable;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 11:15
 * @Description:
 */
@Cacheable
public interface UserRepository extends JpaRepository<User, Integer> {
}
