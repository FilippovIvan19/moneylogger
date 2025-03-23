package com.moneylogger.service.api;

import com.moneylogger.model.BankCard;

import java.util.Optional;

public interface BankCardService extends BaseEntityService<BankCard> {
    Optional<BankCard> findById(Long id);
}
