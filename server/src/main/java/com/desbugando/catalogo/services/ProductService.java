package com.desbugando.catalogo.services;

import com.desbugando.catalogo.dtos.CategoryDto;
import com.desbugando.catalogo.dtos.ProductDto;
import com.desbugando.catalogo.entities.Category;
import com.desbugando.catalogo.entities.Product;
import com.desbugando.catalogo.repositories.CategoryRepository;
import com.desbugando.catalogo.repositories.ProductRepository;
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
public class ProductService {

    private final String MESSAGE_NOT_FOUND_CATEGORY = "Categoria não encontrada";

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(PageRequest pageRequest) {
        Page<Product> pageable = repository.findAll(pageRequest);

        return pageable.map(ProductDto::new);
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(MESSAGE_NOT_FOUND_CATEGORY)
        );
        
        return new ProductDto(entity, entity.getCategories());
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        return new ProductDto(repository.save(entity));
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new ProductDto(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MESSAGE_NOT_FOUND_CATEGORY);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MESSAGE_NOT_FOUND_CATEGORY);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação da integridade do banco de dados");
        }
    }

    private void copyDtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());

        entity.getCategories().clear();
        for(CategoryDto catDto : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDto.getId());
            entity.getCategories().add(category);
        }
    }
}
