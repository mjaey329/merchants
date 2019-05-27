package com.xzjh.passbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("hello index");
        return "index";
    }

}
