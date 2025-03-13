package com.moneylogger.service.impl;

import com.moneylogger.model.OperationRecord;
import com.moneylogger.repository.api.OperationRecordRepository;
import com.moneylogger.service.api.OperationRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class OperationRecordServiceImpl extends AbstractJpaService<OperationRecord> implements OperationRecordService {
    private final OperationRecordRepository operationRecordRepository;

    @Override
    protected JpaRepository<OperationRecord, Long> getRepository() {
        return operationRecordRepository;
    }


    @Override
    public List<OperationRecord> findByParameters(Parameters parameters) {
        return operationRecordRepository.findByDateBetweenAndCategoryIdAndSpendingAndAmountBetweenAndCurrencyCode(
                parameters.startDate, parameters.endDate, parameters.categoryId, parameters.spending,
                parameters.minAmount, parameters.maxAmount, parameters.currencyCode
        );
    }

}
