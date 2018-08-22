package com.ccm;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.awt.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class SpringbootdemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootdemoApplication.class);

    public static void main(String[] args) {


        ApplicationContext context =  SpringApplication.run(SpringbootdemoApplication.class, args);

        ServerProperties serverProperties = context.getBean(ServerProperties.class);


        System.setProperty("java.awt.headless", "false");

        if (Desktop.isDesktopSupported()) {
            try {
                //URI指定网页的地址
                Desktop.getDesktop().browse(new URI("https://blog.csdn.net/weixin_42156742/article/details/81703867"));
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            // 弹出浏览器 - 显示HTTP接口(https)
            try {
                LOGGER.info("弹出浏览器 - 显示HTTP接口(http)");
                URIBuilder uriBuilder = new URIBuilder().setScheme("http") //
                        .setHost("localhost") //
                        .setPort(serverProperties.getPort()).setPath("swagger-ui.html");
                Desktop.getDesktop().browse(uriBuilder.build());
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
        }

    }
    //    每分钟启动
    @Scheduled(cron = "0 0/40 * * * ?")
    public void timerToNow(){
        System.out.println("show now time still alive:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
