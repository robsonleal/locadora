package com.desbugando.catalogo.repositories.tests;

import com.desbugando.catalogo.dtos.ProductDto;
import com.desbugando.catalogo.entities.Category;
import com.desbugando.catalogo.entities.Product;

import java.time.Instant;

public class Factory {
    public static Product createProduct() {
        Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2022-11-22T03:00:00Z"));
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDto createProductDto() {
        Product product = createProduct();
        return new ProductDto(product, product.getCategories());
    }

    public static Category createCategory() {
        return new Category(1L, "Eletronics");
    }
}
