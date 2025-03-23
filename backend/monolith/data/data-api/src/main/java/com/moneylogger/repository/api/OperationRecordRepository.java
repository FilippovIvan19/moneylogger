package com.moneylogger.repository.api;

import com.moneylogger.model.OperationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OperationRecordRepository extends JpaRepository<OperationRecord, Long> {
    List<OperationRecord> findByCategoryId(Long categoryId); // todo include child
    List<OperationRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<OperationRecord> findBySpending(boolean spending);
    List<OperationRecord> findByAmountBetween(Double minAmount, Double maxAmount);
    List<OperationRecord> findByCurrencyCode(String currencyCode);

    List<OperationRecord> findByDateBetweenAndCategoryIdAndSpendingAndAmountBetweenAndCurrencyCode(
            LocalDate startDate, LocalDate endDate, Long categoryId, boolean spending, Double minAmount, Double maxAmount, String currencyCode);

}
