package com.ccm.gzhp;

import com.ccm.base.sys.beans.UserVO;
import com.ccm.bi.core.base.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    @GetMapping({"/index","/"})
    public String index(HttpServletRequest request, Model model){

        UserVO user = (UserVO) request.getSession().getAttribute("user");
        System.out.println(user.getUsername());
        model.addAttribute("user",user);
        return "/index.html";
    }

    @ApiOperation("登录操作")
    @PostMapping("/login")
    public String loginValidate(@RequestParam(name ="username")@Valid String username,
                                @RequestParam(name = "password")@Validated String password,
                                HttpServletResponse response,
                                HttpServletRequest request){
        if (username == null) {
            throw new BizException("账号不可为空");
        }
        if (password == null) {
            throw new BizException("密码不可为空");
        }

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            currentUser.login(token);
        }catch(Exception e){
            throw new BizException("账号密码不匹配");
        }

        UserVO user = new UserVO();
        user.setUsername(username);
        if(currentUser.isAuthenticated()){

            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("username",username);

            return "redirect:/index";
        }else{
            token.clear();
            return "redirect:/login";
        }
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
        return "redirect:login";
    }

}
