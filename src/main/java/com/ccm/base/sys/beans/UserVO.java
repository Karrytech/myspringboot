package com.ccm.base.sys.beans;

import com.ccm.base.sys.po.User;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/21 11:28
 * @Description:
 */
public class UserVO extends User {

    private static final long serialVersionUID = 1L;

    private String password;
    private String username;

    public String getUsername() {
        return this.getName();
    }

    public void setUsername(String username) {
        this.username = username;
        this.setName(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
