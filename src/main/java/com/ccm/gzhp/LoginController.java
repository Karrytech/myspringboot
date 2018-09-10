package com.ccm.gzhp;

import com.ccm.base.sys.po.User;
import com.ccm.bi.core.base.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/9/7 11:20
 * @Description: 登录
 */
@Api
@Controller
public class LoginController {


    @ApiOperation("登录页面")
    @GetMapping("/login")
    public String login(){
        return "/login.html";
    }

    @ApiOperation("应用首页")
    @GetMapping("/index")
    public String index(){
        return "/index.html";
    }

    @ApiOperation("登录操作")
    @PostMapping("/login")
    public String loginValidate(@RequestParam(name ="username") String username,
                                @RequestParam(name = "password") String password,
                                HttpServletResponse response){

        if (username == null) {
            throw new BizException("账号不可为空");
        }
        if (password == null) {
            throw new BizException("密码不可为空");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);

        User user = (User) subject.getPrincipals().getPrimaryPrincipal();

        return "redirect:index";
    }

    @ApiOperation("注销登录")
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request,HttpServletResponse response){

        SecurityUtils.getSubject().logout();

        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                response.addCookie(temp);
            }
        }
        return "redirect:login.html";
    }

}
