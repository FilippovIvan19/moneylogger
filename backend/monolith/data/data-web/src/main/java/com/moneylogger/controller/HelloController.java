package com.moneylogger.controller;

import com.moneylogger.controller.api.BankCardController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello/first/controller")
public class HelloController {
    private final BankCardController bankCardController;

    @GetMapping("/test")
    public String getHello() {
        return "HELLO WORLD!!!";
    }

    @GetMapping("/test1")
    public String getHello1() { // todo move to service
        return bankCardController.findById(3L).getBody().getName();
    }
}
