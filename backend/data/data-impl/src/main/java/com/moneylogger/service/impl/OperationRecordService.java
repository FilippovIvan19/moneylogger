package com.moneylogger.service.impl;

import com.moneylogger.model.OperationRecord;
import com.moneylogger.repository.api.OperationRecordRepository;
import com.moneylogger.service.api.BaseEntityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class OperationRecordService extends AbstractJpaService<OperationRecord> implements BaseEntityService<OperationRecord> {
    private final OperationRecordRepository operationRecordRepository;

    @Autowired
    public OperationRecordService(OperationRecordRepository operationRecordRepository) {
        this.operationRecordRepository = operationRecordRepository;
    }

    @Override
    protected JpaRepository<OperationRecord, Long> getRepository() {
        return operationRecordRepository;
    }


    public List<OperationRecord> findByParameters(Parameters parameters) {
        return operationRecordRepository.findByDateBetweenAndCategoryIdAndSpendingAndAmountBetweenAndCurrencyCode(
                parameters.startDate, parameters.endDate, parameters.categoryId, parameters.spending,
                parameters.minAmount, parameters.maxAmount, parameters.currencyCode
        );
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parameters { // todo refactor to record
        public Date startDate;
        public Date endDate;
        public Long categoryId;
        public boolean spending;
        public Double minAmount;
        public Double maxAmount;
        public String currencyCode;
    }
}
