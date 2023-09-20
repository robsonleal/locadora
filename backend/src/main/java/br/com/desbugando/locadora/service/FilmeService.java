package br.com.desbugando.locadora.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.desbugando.locadora.dto.CategoriaDTO;
import br.com.desbugando.locadora.dto.FilmeDTO;
import br.com.desbugando.locadora.entities.Categoria;
import br.com.desbugando.locadora.entities.Filme;
import br.com.desbugando.locadora.repository.CategoriaRepository;
import br.com.desbugando.locadora.repository.FilmeRepository;
import br.com.desbugando.locadora.service.exceptions.DatabaseException;
import br.com.desbugando.locadora.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Autowired
	private CategoriaRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<FilmeDTO> findAllPaged(Long categoryId, String name, Pageable pageable) {
		Set<Categoria> categories = new HashSet<>();
		if (categoryId != 0) {
			categories.add(categoryRepository.getReferenceById(categoryId));
		}
		Page<Filme> page = repository.find(categories, name, pageable);
		repository.findFilmesWithCategories(new HashSet<>(page.getContent()));
		return page.map(x -> new FilmeDTO(x, x.getCategorias()));
	}

	@Transactional(readOnly = true)
	public FilmeDTO findById(Long id) {
		Optional<Filme> obj = repository.findById(id);
		Filme entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new FilmeDTO(entity, entity.getCategorias());
	}

	@Transactional
	public FilmeDTO insert(FilmeDTO dto) {
		Filme entity = new Filme();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new FilmeDTO(entity);
	}

	@Transactional
	public FilmeDTO update(Long id, FilmeDTO dto) {
		try {
			Filme entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new FilmeDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(FilmeDTO dto, Filme entity) {

		entity.setTitulo(dto.getTitulo());
		entity.setSinopse(dto.getSinopse());
		entity.setCapaURL(dto.getCapaURL());
		entity.setDuracao(dto.getDuracao());
		entity.setAnoLancamento(dto.getAnoLancamento());

		entity.getCategorias().clear();
		for (CategoriaDTO catDto : dto.getCategorias()) {
			Categoria category = categoryRepository.getReferenceById(catDto.getId());
			entity.getCategorias().add(category);
		}
	}
}
