package br.com.desbugando.locadora.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desbugando.locadora.entities.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	Page<Filme> findAll(Pageable pageable);
}
