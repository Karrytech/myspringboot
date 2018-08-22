package com.ccm.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;

@Configuration
public class ApiWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    @Qualifier("interceptor1")
    HandlerInterceptorAdapter interceptor1;

    @Resource(name = "interceptor2")
    HandlerInterceptorAdapter interceptor2;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
        logger.info("UseRegisteredSuffixPatternMatch");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestApiInteceptor()).addPathPatterns("/**");
        registry.addInterceptor(interceptor1).addPathPatterns("/**");
        registry.addInterceptor(interceptor2).addPathPatterns("/**");
    }

}
