package com.moneylogger.service.api;

import com.moneylogger.model.User;

import java.util.Optional;

public interface UserService extends BaseEntityService<User> {
    Optional<User> findByLogin(String login);
}
