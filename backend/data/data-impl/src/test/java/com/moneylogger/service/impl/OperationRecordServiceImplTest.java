package com.moneylogger.service.impl;

import com.moneylogger.model.Category;
import com.moneylogger.model.MonthInfo;
import com.moneylogger.model.OperationRecord;
import com.moneylogger.repository.api.OperationRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OperationRecordServiceImplTest {
    @Mock
    private OperationRecordRepository operationRecordRepository;

    @InjectMocks
    private OperationRecordServiceImpl operationRecordService;

    @Test
    void getMoneyInfoByMonth() {
        int year = 2025;
        Month month = Month.MARCH;

        LocalDate monthStart = LocalDate.of(year, month, 1);
        LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);

        Category categoryFood = new Category(1L, "food", null);
        Category categoryTransport = new Category(2L, "transport", null);

        List<OperationRecord> operationRecords = List.of(
                new OperationRecord(101L, categoryFood, monthStart.plusDays(3), true, 11.2, "RUR", 0.),
                new OperationRecord(102L, categoryFood, monthStart.plusDays(5), true, 13., "RUR", 0.),
                new OperationRecord(103L, categoryFood, monthStart.plusDays(10), false, 15., "RUR", 0.),
                new OperationRecord(104L, categoryTransport, monthStart.plusDays(15), true, 20.5, "RUR", 0.),
                new OperationRecord(105L, categoryTransport, monthStart.plusDays(16), false, 25., "RUR", 0.),
                new OperationRecord(106L, categoryTransport, monthStart.plusDays(20), true, 46., "RUR", 0.)
        );

        Mockito.when(operationRecordRepository.findByDateBetween(monthStart, monthEnd))
                .thenReturn(operationRecords);

        MonthInfo result = operationRecordService.getMoneyInfoByMonth(year, month);

        assertEquals(11.2 + 13. + 20.5 + 46., result.getExpensesAmount());
        assertEquals(4, result.getExpenses().size());
        Map<String, Double> expensesAmountByCategory = result.getExpensesAmountByCategory();
        assertEquals(11.2 + 13., expensesAmountByCategory.get(categoryFood.getName()));
        assertEquals(20.5 + 46., expensesAmountByCategory.get(categoryTransport.getName()));

    }
}