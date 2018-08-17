package com.ccm.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiWebMvcConfigurer implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
        logger.info("UseRegisteredSuffixPatternMatch");
    }
}
