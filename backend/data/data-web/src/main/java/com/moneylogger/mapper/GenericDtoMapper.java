package com.moneylogger.mapper;

import com.moneylogger.model.Identifiable;

import java.util.List;

public interface GenericDtoMapper<E extends Identifiable, D> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dto);
    List<D> toDto(List<E> entity);
}
