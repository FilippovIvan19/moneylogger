package com.moneylogger.controller.api;

import com.moneylogger.model.BankCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "BankCardController", url = "${accounts-service.url}", path = "/bankCards")
public interface BankCardController {
    @GetMapping("/test")
    String getHello();

    @GetMapping("byId/{id}")
    ResponseEntity<BankCard> findById(@PathVariable("id") Long id);
}
