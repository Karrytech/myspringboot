package com.ccm.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.env")
public class EnvProperties {
	// 环境模式：dev为开发模式、prod为生产模式
	private String mode;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
