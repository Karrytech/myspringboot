package com.ccm.bi.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/17 11:15
 * @Description:
 */
@Api(tags = "后台接口首页")
@RestController("biApiHomeController")
@RequestMapping("/bi")
public class ApiHomeController {

    @GetMapping("/")
    @ApiOperation(value = "/",notes = "跳转到swagger-ui.html")
    public ResponseEntity<Void> home() {
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).header("Location", "/swagger-ui.html").build();
    }
}
