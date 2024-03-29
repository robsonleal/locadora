package br.com.desbugando.locadora.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_CATEGORIAS")
@EntityListeners(AuditingEntityListener.class)
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OID_CATEGORIA")
	private Long id;

	@Column(name = "TXT_NOME")
	private String nome;

	@CreatedDate
	@Column(name = "DAT_CRIACAO", nullable = false, updatable = false)
	private Instant criadoEm;

	@LastModifiedDate
	@Column(name = "DAT_ATUALIZACAO", nullable = false)
	private Instant atualizadoEm;

	@ManyToMany(mappedBy = "categorias")
	private Set<Filme> filmes = new HashSet<>();

	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}
