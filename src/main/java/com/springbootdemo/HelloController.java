
package com.springbootdemo;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ApiOperation("hello spring boot")
    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        String hello = "i want you";
        System.out.println(hello);


        return "hello,this is a springboot demo";
    }
    @PostMapping("hi")
    public String hi() {
        String hello = "i want you";
        System.out.println(hello);


        return "hello,this is a springboot demo";
    }
}