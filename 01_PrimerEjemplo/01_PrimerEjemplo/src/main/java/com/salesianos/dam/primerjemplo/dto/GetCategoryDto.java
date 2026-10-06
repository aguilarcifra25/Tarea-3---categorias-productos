package com.salesianos.dam.primerjemplo.dto;

import com.salesianos.dam.primerjemplo.model.Category;

public record GetCategoryDto(
        Long id,
        String name
) {

    public static GetCategoryDto of(Category c) {
        return new GetCategoryDto(c.getId(), c.getName());
    }

}
