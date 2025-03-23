package com.moneylogger.controller;


import com.moneylogger.aop.api.LogCall;
import com.moneylogger.dto.OperationRecordDto;
import com.moneylogger.mapper.OperationRecordMapper;
import com.moneylogger.model.MonthInfo;
import com.moneylogger.model.OperationRecord;
import com.moneylogger.service.api.OperationRecordService;
import com.moneylogger.validation.group.OnCreate;
import com.moneylogger.validation.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/operationRecords")
public class OperationRecordController {
    private final OperationRecordService operationRecordService;
    private final OperationRecordMapper operationRecordMapper;

    @LogCall
    @GetMapping("/all")
    public ResponseEntity<List<OperationRecordDto>> findAll() {
        List<OperationRecord> allOperationRecords = operationRecordService.findAll();
        return ResponseEntity.ok(operationRecordMapper.toDto(allOperationRecords));
    }

    @LogCall
    @GetMapping("byParameters")
    public ResponseEntity<List<OperationRecordDto>> findByParameters(@RequestBody OperationRecordService.Parameters parameters) {
        List<OperationRecord> operationRecords = operationRecordService.findByParameters(parameters);
        return ResponseEntity.ok().body(operationRecordMapper.toDto(operationRecords));
    }

    @LogCall
    @GetMapping("byId/{id}")
    public ResponseEntity<OperationRecordDto> findById(@PathVariable("id") Long id) {
        Optional<OperationRecord> operationRecord = operationRecordService.findById(id);
        return ResponseEntity.of(operationRecord.map(operationRecordMapper::toDto));
    }

    @LogCall
    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        operationRecordService.deleteById(id);
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was deleted");
    }

    @LogCall
    @PostMapping(value = "")
    public ResponseEntity<String> create(@Validated(OnCreate.class) @RequestBody OperationRecordDto operationRecord) {
        operationRecordService.create(operationRecordMapper.toEntity(operationRecord));
        return ResponseEntity.ok().body("operationRecord with id = " + operationRecord.getId() + " was created"); //todo use created entity as return value
    }

    @LogCall
    @PutMapping(value = "")
    public ResponseEntity<String> update(@Validated(OnUpdate.class) @RequestBody OperationRecordDto operationRecord) {
        Long id = operationRecord.getId();
        operationRecordService.update(operationRecordMapper.toEntity(operationRecord));
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was updated");
    }

    @LogCall
    @GetMapping("forMonth/{year}/{month}")
    public ResponseEntity<MonthInfo> getMoneyInfoByMonth(@PathVariable int year, @PathVariable int month) {
        return ResponseEntity.ok().body(operationRecordService.getMoneyInfoByMonth(year, Month.of(month))); // todo convert to dto // maybe move all dtos to api module ?
    }

}

