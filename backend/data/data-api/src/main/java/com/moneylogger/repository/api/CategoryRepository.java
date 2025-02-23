package com.moneylogger.repository.api;

import com.moneylogger.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

//    boolean existsByTitle(String title);

//    @Query(value = "WITH RECURSIVE a AS(SELECT id, parent_id, title FROM Categorys WHERE id = :id UNION SELECT t.id, t.parent_id, t.title FROM Categorys t JOIN a ON t.id = a.parent_id) SELECT id, parent_id, title FROM a", nativeQuery = true)
//    List<Category> findWithParents(Long id);

//    Category findById(Long parentId);

    List<Category> findByParentId(Long parentId);

    List<Category> findByName(String name);

//    @Query(value = "SELECT max(id) FROM Categorys", nativeQuery = true)
//    Long getLastId();
}
