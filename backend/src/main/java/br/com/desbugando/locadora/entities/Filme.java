package br.com.desbugando.locadora.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TB_FILMES")
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OID_FILME")
	private Long id;

	@Column(name = "TXT_TITULO")
	private String titulo;

	@Column(name = "TXT_SINOPSE", columnDefinition = "TEXT")
	private String sinopse;

	@Column(name = "NUM_DURACAO")
	private int duracao;

	@Column(name = "TXT_CAPA_URL")
	private String capaURL;

	@Column(name = "NUM_ANO_LANCAMENTO")
	private int anoLancamento;

	@ManyToMany
	@JoinTable(name = "TB_FILME_CATEGORIA", 
							joinColumns = @JoinColumn(name = "OID_FILME"),
							inverseJoinColumns = @JoinColumn(name = "OID_CATEGORIA"))
	Set<Categoria> categorias = new HashSet<>();
}
