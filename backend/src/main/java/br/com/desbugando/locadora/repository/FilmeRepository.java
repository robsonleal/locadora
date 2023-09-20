package br.com.desbugando.locadora.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.desbugando.locadora.entities.Categoria;
import br.com.desbugando.locadora.entities.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

	@Query("SELECT DISTINCT obj FROM Filme obj " +
	       "WHERE obj.categorias IN :categorias " +
			   "AND LOWER(obj.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
	Page<Filme> find(@Param("categorias") Set<Categoria> categorias, @Param("titulo") String titulo, Pageable pageable);

	@Query("SELECT obj FROM Filme obj JOIN FETCH obj.categorias WHERE obj IN :filmes")
	Set<Filme> findFilmesWithCategories(@Param("filmes") Set<Filme> filmes);
}
