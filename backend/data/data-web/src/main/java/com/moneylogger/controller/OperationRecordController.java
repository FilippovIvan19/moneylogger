package com.moneylogger.controller;


import com.moneylogger.dto.OperationRecordDto;
import com.moneylogger.mapper.OperationRecordMapper;
import com.moneylogger.model.OperationRecord;
import com.moneylogger.service.impl.OperationRecordService;
import com.moneylogger.validation.group.OnCreate;
import com.moneylogger.validation.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ResponseEntity<List<OperationRecordDto>> findAll() {
        log.debug("requested: operationRecord get     (all)");
        System.out.println("GETTING allOperationRecords");
        List<OperationRecord> allOperationRecords = operationRecordService.findAll();
        return ResponseEntity.ok(operationRecordMapper.toDto(allOperationRecords));
    }

    @GetMapping("byParameters")
    public ResponseEntity<List<OperationRecordDto>> findByParameters(@RequestBody OperationRecordService.Parameters parameters) {
        log.debug("requested: operationRecord get    (parameters = {})", parameters);
        List<OperationRecord> operationRecords = operationRecordService.findByParameters(parameters);
        return ResponseEntity.ok().body(operationRecordMapper.toDto(operationRecords));
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<OperationRecordDto> findById(@PathVariable("id") Long id) {
        log.debug("requested: operationRecord get      (id = {})", id);
        Optional<OperationRecord> operationRecord = operationRecordService.findById(id);
        return ResponseEntity.of(operationRecord.map(operationRecordMapper::toDto));
    }

    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: operationRecord  delete (id = {})", id);
        operationRecordService.deleteById(id);
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was deleted");
    }

    @PostMapping(value = "")
    public ResponseEntity<String> create(@Validated(OnCreate.class) @RequestBody OperationRecordDto operationRecord) {
        log.debug("requested: operationRecord  create (amount = {}{}, categoryId = {})", operationRecord.isSpending() ? "-" : "+", operationRecord.getAmount(), operationRecord.getCategoryId());
        operationRecordService.create(operationRecordMapper.toEntity(operationRecord));
        return ResponseEntity.ok().body("operationRecord with id = " + operationRecord.getId() + " was created"); //todo use created entity as return value
    }

    @PutMapping(value = "")
    public ResponseEntity<String> update(@Validated(OnUpdate.class) @RequestBody OperationRecordDto operationRecord) {
        Long id = operationRecord.getId();
        log.debug("requested: operationRecord  update (id = {})", id);
        operationRecordService.update(operationRecordMapper.toEntity(operationRecord));
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was updated");
    }

}

