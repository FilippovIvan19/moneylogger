package com.moneylogger.controller;


import com.moneylogger.aop.api.LogCall;
import com.moneylogger.model.User;
import com.moneylogger.service.impl.UserService;
import com.moneylogger.validation.group.OnCreate;
import com.moneylogger.validation.group.OnUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @LogCall
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @LogCall
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("byId/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
        if (!Objects.equals(id, user.getId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.of(userService.findById(id));
    }

    @LogCall
    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was deleted");
    }

    @LogCall
    @PostMapping(value = "")
    public ResponseEntity<String> create(@Validated(OnCreate.class) @RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok().body("operationRecord with id = " + user.getId() + " was created"); //todo use created entity as return value
    }

    @LogCall
    @PutMapping(value = "")
    public ResponseEntity<String> update(@Validated(OnUpdate.class) @RequestBody User user) {
        Long id = user.getId();
        userService.update(user);
        return ResponseEntity.ok().body("operationRecord with id = " + id + " was updated");
    }

}

