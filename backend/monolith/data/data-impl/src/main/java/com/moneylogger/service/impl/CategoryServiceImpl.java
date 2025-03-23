package com.moneylogger.service.impl;

import com.moneylogger.model.Category;
import com.moneylogger.repository.api.CategoryRepository;
import com.moneylogger.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbstractJpaService<Category> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }


//    public List<Category> findWithParents(Long id) {
//            return categoryRepository.findWithParents(id);
//    }

    @Override
    public List<Category> getChildren(Long id) {
        return categoryRepository.findByParentId(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
