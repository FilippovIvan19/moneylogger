package com.moneylogger.controller;


import com.moneylogger.model.OperationRecord;
import com.moneylogger.service.impl.OperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
//@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/operationRecords")
public class OperationRecordController {
    private final OperationRecordService operationRecordService;

    @Autowired
    public OperationRecordController(OperationRecordService operationRecordService) {
        this.operationRecordService = operationRecordService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OperationRecord>> findAll() {
        log.debug("requested: operationRecord get     (all)");
        return ResponseEntity.ok(operationRecordService.findAll());
    }

    @GetMapping("byParameters")
    public ResponseEntity<List<OperationRecord>> findByParameters(@RequestBody OperationRecordService.Parameters parameters) {
        log.debug("requested: operationRecord get    (parameters = {})", parameters);
        return ResponseEntity.ok().body(operationRecordService.findByParameters(parameters));
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<OperationRecord> findById(@PathVariable("id") Long id) {
        log.debug("requested: operationRecord get      (id = {})", id);
        Optional<OperationRecord> operationRecord = operationRecordService.findById(id);
        return ResponseEntity.of(operationRecord);
    }

    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: operationRecord  delete (id = {})", id);
        operationRecordService.deleteById(id);
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was deleted");
    }

    @PostMapping(value = "")
    public ResponseEntity<String> create(@RequestBody OperationRecord operationRecord) {
        log.debug("requested: operationRecord  create (amount = {}{}, categoryId = {})", operationRecord.isSpending() ? "-" : "+", operationRecord.getAmount(), operationRecord.getCategoryId());
        operationRecordService.create(operationRecord);
        return ResponseEntity.ok().body("operationRecord with id = " + operationRecord.getId() + " was created");
    }

    @PutMapping(value = "")
    public ResponseEntity<String> update(@RequestBody OperationRecord operationRecord) {
        Long id = operationRecord.getId();
        log.debug("requested: operationRecord  update (id = {})", id);
        operationRecordService.update(operationRecord);
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was updated");
    }

}

