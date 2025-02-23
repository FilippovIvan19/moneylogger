package com.moneylogger.repository.api;

import com.moneylogger.model.OperationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OperationRecordRepository extends JpaRepository<OperationRecord, Long> {
    List<OperationRecord> findByCategoryId(Long categoryId); // todo include child
    List<OperationRecord> findByDateBetween(Date startDate, Date endDate);
    List<OperationRecord> findBySpending(boolean spending);
    List<OperationRecord> findByAmountBetween(Double minAmount, Double maxAmount);
    List<OperationRecord> findByCurrencyCode(String currencyCode);

    List<OperationRecord> findByDateBetweenAndCategoryIdAndSpendingAndAmountBetweenAndCurrencyCode(
            Date startDate, Date endDate, Long categoryId, boolean spending, Double minAmount, Double maxAmount, String currencyCode);

}
