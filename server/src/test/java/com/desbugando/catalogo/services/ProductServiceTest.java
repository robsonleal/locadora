package com.desbugando.catalogo.services;

import com.desbugando.catalogo.dtos.ProductDto;
import com.desbugando.catalogo.entities.Category;
import com.desbugando.catalogo.entities.Product;
import com.desbugando.catalogo.repositories.CategoryRepository;
import com.desbugando.catalogo.repositories.ProductRepository;
import com.desbugando.catalogo.repositories.tests.Factory;
import com.desbugando.catalogo.services.exceptions.DatabaseException;
import com.desbugando.catalogo.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    Product product;
    Category category;
    ProductDto productDto;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 100L;
        dependentId = 10L;
        product = Factory.createProduct();
        productDto = Factory.createProductDto();
        category = Factory.createCategory();
        PageImpl<Product> page = new PageImpl<>(List.of(product));

        when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
        when(repository.findById(existingId)).thenReturn(Optional.of(product));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
        when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        when(repository.getReferenceById(existingId)).thenReturn(product);
        doThrow(ResourceNotFoundException.class).when(repository).getReferenceById(nonExistingId);

        when(categoryRepository.getReferenceById(existingId)).thenReturn(category);
        doThrow(ResourceNotFoundException.class).when(categoryRepository).getReferenceById(nonExistingId);

        doNothing().when(repository).deleteById(existingId);
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        doThrow(DatabaseException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update(nonExistingId, productDto));
    }

    @Test
    public void updateShouldReturnProductDtoWhenIdExists() {
        ProductDto result = service.update(existingId, productDto);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistingId));
    }

    @Test
    public void findByIdShouldReturnProductDtoWhenIdExists() {
        ProductDto result = service.findById(existingId);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findAllPagedShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductDto> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);
        verify(repository).findAll(pageable);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExist() {
        Assertions.assertDoesNotThrow(() -> service.delete(existingId));
        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingId));
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> service.delete(dependentId));
    }
}
