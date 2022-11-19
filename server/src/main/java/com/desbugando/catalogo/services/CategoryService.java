package com.desbugando.catalogo.services;

import com.desbugando.catalogo.dtos.CategoryDto;
import com.desbugando.catalogo.entities.Category;
import com.desbugando.catalogo.repositories.CategoryRepository;
import com.desbugando.catalogo.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<Category> list = repository.findAll();

        return list.stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return new CategoryDto(
            repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encontrada!")
            )
        );
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        Category entity = new Category();
        entity.setNome(dto.getName());
        return new CategoryDto(repository.save(entity));
    }
}
