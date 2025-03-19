package com.moneylogger.service.api;

import com.moneylogger.model.OperationRecord;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

public interface OperationRecordService extends BaseEntityService<OperationRecord> {
    List<OperationRecord> findByParameters(Parameters parameters);

    @AllArgsConstructor
    @NoArgsConstructor
    class Parameters { // todo refactor to record ?
        public LocalDate startDate; // todo replace with LocalDate?
        public LocalDate endDate;
        public Long categoryId;
        public boolean spending;
        public Double minAmount;
        public Double maxAmount;
        public String currencyCode;
    }
}
