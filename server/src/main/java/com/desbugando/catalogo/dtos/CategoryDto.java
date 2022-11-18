package com.desbugando.catalogo.dtos;

import com.desbugando.catalogo.entities.Category;

public class CategoryDto {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getNome();
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
