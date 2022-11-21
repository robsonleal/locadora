package com.desbugando.catalogo.services;

import com.desbugando.catalogo.dtos.CategoryDto;
import com.desbugando.catalogo.entities.Category;
import com.desbugando.catalogo.repositories.CategoryRepository;
import com.desbugando.catalogo.services.exceptions.DatabaseException;
import com.desbugando.catalogo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDto> findAllPaged(PageRequest pageRequest) {
        Page<Category> pageable = repository.findAll(pageRequest);

        return pageable.map(CategoryDto::new);
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return new CategoryDto(
            repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada!")
            )
        );
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        return new CategoryDto(repository.save(entity));
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto dto) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            return new CategoryDto(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Categoria não encontrada!");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Categoria não encontrada!");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação da integridade do banco de dados");
        }
    }
}
