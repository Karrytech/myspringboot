package com.ccm.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/22 09:39
 * @Description:
 */
@Configuration
public class HandlerInterceptorConfig {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public HandlerInterceptorAdapter interceptor1(){
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                logger.info("interceptor1111111111111.............preHandle");
                return true;
            }
        };
    }
    @Bean
    public HandlerInterceptorAdapter interceptor2(){
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                logger.info("interceptor22222222222222222222222222222.............");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                logger.info("interceptor22222222222222222222222222222.............postHandle");
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                logger.info("interceptor22222222222222222222222222222.............postHandle");
            }
        };
    }
}
