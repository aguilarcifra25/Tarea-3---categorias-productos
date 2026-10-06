package com.salesianos.dam.primerjemplo.utils;

import com.salesianos.dam.primerjemplo.dto.EditCategoryDto;
import com.salesianos.dam.primerjemplo.dto.EditProductDto;
import com.salesianos.dam.primerjemplo.service.CategoryService;
import com.salesianos.dam.primerjemplo.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final CategoryService categoryService;
    private final ProductService productService;

    @PostConstruct
    public void initData() {

        List.of(
                new EditCategoryDto("Entrantes"),
                new EditCategoryDto("Principales"),
                new EditCategoryDto("Postres"),
                new EditCategoryDto("Bebidas")
        ).forEach(categoryService::addCategory);

        List.of(
                new EditProductDto("Pan y picos", 1.0, "Servicio de mesa"),
                new EditProductDto("Croquetas de jamón", 8.5, "Ración de 8 unidades"),
                new EditProductDto("Ensaladilla rusa", 7.0, "Con ventresca de atún"),
                new EditProductDto("Solomillo al whisky", 16.9, "Con patatas fritas caseras"),
                new EditProductDto("Tarta de queso", 5.5, "Porción individual"),
                new EditProductDto("Agua mineral", 1.8, "Botella de 50 cl"),
                new EditProductDto("Cerveza", 2.5, "Caña de 20 cl")
        ).forEach(productService::addProduct);

    }

}
