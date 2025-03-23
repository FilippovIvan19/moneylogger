package com.moneylogger.mapper;

import com.moneylogger.dto.OperationRecordDto;
import com.moneylogger.model.OperationRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationRecordMapper extends GenericDtoMapper<OperationRecord, OperationRecordDto> {
    // todo use some pattern for generation?
}
