package com.moneylogger.service.api;

import com.moneylogger.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface BaseEntityService<T extends Identifiable> {
    Optional<T> findById(Long id);
    void deleteById(Long id);
    void deleteByIdOnlyIfExists(Long id) throws IllegalStateException;
    List<T> findAll();
    public <S extends T> S create(S category);
    public <S extends T> S update(S category);
}
