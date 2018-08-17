package com.ccm.bi.task.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class Task4Quartz1{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	public void timeTask() {
		logger.info("定时任务----task---开启。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		try {
			//URI指定网页的地址
			Desktop.getDesktop().browse(new URI("https://blog.csdn.net/weixin_42156742/article/details/81383628"));
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}
	public void timeTask1() {
		logger.info("定时任务----timeTask1------开启。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		try {
			//URI指定网页的地址
			Desktop.getDesktop().browse(new URI("https://blog.csdn.net/weixin_42156742/article/details/81703867"));
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

	

}
