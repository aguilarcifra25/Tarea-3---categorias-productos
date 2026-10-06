package com.salesianos.dam.primerjemplo.controller;

import com.salesianos.dam.primerjemplo.dto.EditCategoryDto;
import com.salesianos.dam.primerjemplo.dto.GetCategoryDto;
import com.salesianos.dam.primerjemplo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<GetCategoryDto> addCategory(@RequestBody EditCategoryDto category) {

        return ResponseEntity.status(201)
                .body(GetCategoryDto.of(categoryService.addCategory(category)));
    }

    @GetMapping
    public ResponseEntity<List<GetCategoryDto>> getAllCategories() {

        return ResponseEntity.ok(
                categoryService.getAll()
                        .stream()
                        .map(GetCategoryDto::of)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryDto> getCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(GetCategoryDto.of(categoryService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody EditCategoryDto category) {

        return ResponseEntity.ok(
                GetCategoryDto.of(categoryService.updateCategory(id, category)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
