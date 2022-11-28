package com.desbugando.catalogo.services;

import com.desbugando.catalogo.dtos.RoleDto;
import com.desbugando.catalogo.dtos.UserDto;
import com.desbugando.catalogo.dtos.UserInsertDto;
import com.desbugando.catalogo.dtos.UserUpdateDto;
import com.desbugando.catalogo.entities.Role;
import com.desbugando.catalogo.entities.User;
import com.desbugando.catalogo.repositories.RoleRepository;
import com.desbugando.catalogo.repositories.UserRepository;
import com.desbugando.catalogo.services.exceptions.DatabaseException;
import com.desbugando.catalogo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final String MESSAGE_NOT_FOUND_PRODUCT = "Usuário não encontrado";

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);

        return list.map(UserDto::new);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        User entity = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(MESSAGE_NOT_FOUND_PRODUCT)
        );
        
        return new UserDto(entity);
    }

    @Transactional
    public UserDto insert(UserInsertDto dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return new UserDto(repository.save(entity));
    }

    @Transactional
    public UserDto update(Long id, UserUpdateDto dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new UserDto(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(MESSAGE_NOT_FOUND_PRODUCT);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MESSAGE_NOT_FOUND_PRODUCT);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação da integridade do banco de dados");
        }
    }

    private void copyDtoToEntity(UserDto dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for(RoleDto roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);
        }
    }
}
