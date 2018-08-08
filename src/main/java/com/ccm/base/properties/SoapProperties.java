package com.ccm.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Project: his-api-base</p>
 * <p>Description: Spring配置文件.</p>
 * <p>Copyright (c) 2017 Karrytech (Shanghai/Beijing) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:linguo@karrytech.com">Lin Guo</a>
 */
@ConfigurationProperties(prefix = "his.soap")
public class SoapProperties {
    // Web Service请求路径前缀
    private String pathPrefix;

    public String getPathPrefix() {
        return pathPrefix;
    }

    public void setPathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }
}
