package com.moneylogger.service.api;

import com.moneylogger.model.Category;

import java.util.List;

public interface CategoryService extends BaseEntityService<Category> {
    List<Category> getChildren(Long id);
    List<Category> findByName(String name);
}
