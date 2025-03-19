package com.moneylogger.dto;

import com.moneylogger.validation.group.OnCreate;
import com.moneylogger.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public class OperationRecordDto { // todo use java record?

    @NotNull(message = "Id must not be null.", groups = OnUpdate.class)
    private Long id;

    private Long categoryId;

    private Date date;

    private boolean spending;

    @Positive(message = "Amount must be positive.", groups = {OnCreate.class, OnUpdate.class})
    private Double amount;

    @Length(max = 10, groups = {OnCreate.class, OnUpdate.class})
    private String currencyCode;

    @PositiveOrZero(message = "Cashback amount must be positive or zero.", groups = {OnCreate.class, OnUpdate.class})
    private Double cashBack;
}
