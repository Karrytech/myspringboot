package org.cassidy.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/9/12 10:21
 * @Description:
 */
public class ShiroDemo1 {

    public static void main(String[] args) {

        //SecurityManager -- >factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        Factory<SecurityManager> factory2 = DefaultSecurityManager::new;
        SecurityManager manager = factory.getInstance();
        //当前用户Subject-->SecurityUtils
        SecurityUtils.setSecurityManager(manager);
        //当前用户
        Subject user = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin","123");

        try {
            user.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
