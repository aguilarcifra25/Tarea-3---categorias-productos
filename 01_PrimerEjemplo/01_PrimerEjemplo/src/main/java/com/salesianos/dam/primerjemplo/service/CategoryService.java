package com.salesianos.dam.primerjemplo.service;


import com.salesianos.dam.primerjemplo.dto.EditCategoryDto;
import com.salesianos.dam.primerjemplo.error.CategoryNotFoundException;
import com.salesianos.dam.primerjemplo.error.InvalidCategoryException;
import com.salesianos.dam.primerjemplo.model.Category;
import com.salesianos.dam.primerjemplo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return categories;
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category addCategory(EditCategoryDto editCategoryDto) {
        validateCategory(editCategoryDto);
        return categoryRepository.save(editCategoryDto.to());
    }

    public Category updateCategory(Long id, EditCategoryDto editCategoryDto) {

        return categoryRepository.findById(id)
                .map(c -> {
                    validateCategory(editCategoryDto);
                    c.setName(editCategoryDto.name());
                    return categoryRepository.save(c);
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void deleteCategory(Long id) {
        // Igual que con los productos, el borrado es idempotente:
        // si la categoría no existe, no se lanza ninguna excepción.
        categoryRepository.deleteById(id);
    }

    private void validateCategory(EditCategoryDto editCategoryDto) {
        if (!StringUtils.hasText(editCategoryDto.name())) {
            throw new InvalidCategoryException("Category name is required");
        }
    }

}
