package com.ccm.base.config;

import com.ccm.base.sys.beans.UserVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro realm
 */
public class SecurityRealm extends AuthorizingRealm {

    @Value("${app.username")
    private String username;

    @Value("${app.password}")
    private String password;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String currentUserName = (String)principalCollection.getPrimaryPrincipal();
        List<String> roles = new ArrayList<String>();  //角色
        List<String> prems = new ArrayList<String>(); //权限
        roles.add("baidu");
        roles.add("google");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(prems);
        return authorizationInfo;
    }

    /**
     * 认证，验证当前登录的Subject
     * LoginController.login 方法中调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

//       String userName = (String)authenticationToken.getPrincipal();
       //这里暂时写死， user： admin;password： admin
       // UserVo user = loginClient.selectUserByName(userName);
       UserVO user = new UserVO();
       user.setName(username);
       user.setPassword(new Md5Hash(password).toHex()); //与SecurityManager加密方式一致，使用一次散列，并转为16进制，验证方能通过。
       if(user == null){
           throw new UnknownAccountException();//没找到帐号
       }
       //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
