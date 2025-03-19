package com.moneylogger.service.impl;

import com.moneylogger.model.MonthInfo;
import com.moneylogger.model.OperationRecord;
import com.moneylogger.repository.api.OperationRecordRepository;
import com.moneylogger.service.api.OperationRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @Override
    public MonthInfo getMoneyInfoByMonth(int year, Month month) {
        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);
        List<OperationRecord> monthRecords = operationRecordRepository.findByDateBetween(monthStart, monthEnd);

        List<OperationRecord> expenses = monthRecords.stream().filter(OperationRecord::isSpending).toList();
        Double expensesAmount = expenses.stream().mapToDouble(OperationRecord::getAmount).sum();
        Map<String, Double> expensesAmountByCategory = expenses.stream().collect(Collectors.groupingBy(
                rec -> rec.getCategory().getName(), Collectors.summingDouble(OperationRecord::getAmount)));

        MonthInfo result = new MonthInfo(expenses, expensesAmount, expensesAmountByCategory);
        return result;
    }

}
