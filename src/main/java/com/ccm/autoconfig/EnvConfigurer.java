package com.ccm.autoconfig;

import com.ccm.base.properties.EnvProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ EnvProperties.class })
public class EnvConfigurer {

}
