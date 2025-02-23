package com.moneylogger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello/first/controller")
public class HelloController {

    @GetMapping("/test")
    public String getHello() {
        return "HELLO WORLD!!!";
    }

    @GetMapping("/test1")
    public String getHello1() {
        return "HELLO WORLD1!!!";
    }
}
