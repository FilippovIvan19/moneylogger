package com.moneylogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MoneyLoggerApp {
    public static void main(String[] args) {
        SpringApplication.run(MoneyLoggerApp.class, args);
    }
}
