package com.moneylogger.controller;


import com.moneylogger.model.Category;
import com.moneylogger.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

//    @RolesAllowed("ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        log.debug("requested: category get     (all)");
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("byName/{name}")
    public ResponseEntity<List<Category>> findByName(@PathVariable(name = "name") String name) {
        log.debug("requested: category get    (name = {})", name);
        return ResponseEntity.ok().body(categoryService.findByName(name));
    }

//    @RolesAllowed("ADMIN")
    @GetMapping("byId/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        log.debug("requested: category get      (id = {})", id);
        Optional<Category> category = categoryService.findById(id);
        return ResponseEntity.of(category);
    }

//    @GetMapping("withParentsById/{id}")
//    public ResponseEntity<List<Category>> findWithParents(@PathVariable("id") Long id) {
//        log.debug("requested: category get with parents (id = {})", id);
//        List<Category> categories = categoryService.findWithParents(id);
//        return ResponseEntity.ok(categories);
//    }

    @GetMapping("children/{id}")
    public ResponseEntity<List<Category>> getChildren(@PathVariable("id") Long id) {
        log.debug("requested: category get children (id = {})", id);
        List<Category> categories = categoryService.getChildren(id);
        return ResponseEntity.ok(categories);
    }

//    @RolesAllowed("ADMIN") /todo check
    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        log.debug("requested: category  delete (id = {})", id);
        categoryService.deleteById(id);
        return ResponseEntity.ok().body("category with id = " + id + " was deleted");
    }

//    @RolesAllowed("ADMIN")
    @PostMapping(value = "") // todo DTO with validation
    public ResponseEntity<String> create(@RequestBody Category category) {
        log.debug("requested: category  create (name = {}, parentId = {})", category.getParentId(), category.getParentId());
        categoryService.create(category);
        return ResponseEntity.ok().body("category with id = " + category.getId() + " was created");
    }

//    @RolesAllowed("ADMIN")
    @PutMapping(value = "")
    public ResponseEntity<String> update(@RequestBody Category category) {
        Long id = category.getId();
        log.debug("requested: category  update (id = {})", id);
        categoryService.update(category);
        return ResponseEntity.ok().body("category with id = " + id + " was updated");
    }
    
//    @RolesAllowed("ADMIN")
//    @GetMapping("/lastCategoryId")
//    public ResponseEntity<Long> getLastId() {
//        log.debug("requested: last category id");
//        return ResponseEntity.ok(categoryService.getLastId());
//    }
}

