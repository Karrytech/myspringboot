package org.cassidy.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/9/12 16:08
 * @Description:
 */
public class AuthenticationTest {
    SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void addUser(){

        realm.addAccount("admin","admin");
    }

    @Test
    public void tetAuthentication(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");

        defaultSecurityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");

        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());

        subject.checkRole("");
        subject.hasRole("");
        subject.checkPermission("");
    }
}
