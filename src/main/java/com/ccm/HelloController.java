
package com.ccm;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ApiOperation("hello spring boot")
    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        String hello = "i want you";
        System.out.println(hello);
//        int i = 12/0;
        return "hello,this is a springboot demo";
    }

    @GetMapping("iso88591")
    @ResponseBody
    public String iso2utf(@RequestParam String test){
        System.out.println(test);
        return test;
    }

    @PostMapping("/hi")
    @ResponseBody
    public String hi() {
        String hello = "i want you";
        System.out.println(hello);
        return "hello,this is a springboot demo";
    }
}