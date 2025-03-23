package com.moneylogger.controller.impl;

import com.moneylogger.controller.api.BankCardController;
import com.moneylogger.model.BankCard;
import com.moneylogger.service.api.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankCards")
@RequiredArgsConstructor
public class BankCardControllerImpl implements BankCardController {
    private final BankCardService bankCardService;

    @Override
    public String getHello() {
        return "HELLO WORLD!!!";
    }

    @Override
    public ResponseEntity<BankCard> findById(@PathVariable("id") Long id) {
        return ResponseEntity.of(bankCardService.findById(id));
    }
}
