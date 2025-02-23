package com.moneylogger.service.impl;

import com.moneylogger.model.Category;
import com.moneylogger.repository.api.CategoryRepository;
import com.moneylogger.service.api.BaseEntityService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CategoryService extends AbstractJpaService<Category> implements BaseEntityService<Category> {
//    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired // todo find best way to use
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }


//    public List<Category> findWithParents(Long id) {
//            return categoryRepository.findWithParents(id);
//    }

    public List<Category> getChildren(Long id) {
        return categoryRepository.findByParentId(id);
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
