package com.moneylogger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data // todo create packages for entities, dtos, etc
public class MonthInfo { // todo add custom timeRange, add objects for all earnings-related data and expenses-related data
    List<OperationRecord> expenses;
    Double expensesAmount;
    Map<String, Double> expensesAmountByCategory;
}
