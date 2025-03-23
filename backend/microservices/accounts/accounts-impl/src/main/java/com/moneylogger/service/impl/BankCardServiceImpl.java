package com.moneylogger.service.impl;

import com.moneylogger.model.BankCard;
import com.moneylogger.service.api.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankCardServiceImpl implements BankCardService {

    private final ApplicationContext context;

    private final Map<Long, BankCard> storage = Map.of(
            1L, new BankCard(1L, "sber debit card", "1427", BankCard.Type.DEBIT),
            2L, new BankCard(2L, "alfa credit", "1268", BankCard.Type.CREDIT),
            3L, new BankCard(3L, "my favorite card", "6585", BankCard.Type.DEBIT)
    );

    @Override
    public Optional<BankCard> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void deleteByIdOnlyIfExists(Long id) throws IllegalStateException {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<BankCard> findAll() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public <S extends BankCard> S create(S category) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public <S extends BankCard> S update(S category) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
