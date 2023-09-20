package br.com.desbugando.locadora.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.desbugando.locadora.entities.Categoria;
import br.com.desbugando.locadora.entities.Filme;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {

	private Long id;
	
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo requerido")
	private String titulo;

	@NotBlank(message = "Campo requerido")
	private String sinopse;
	
	@Positive(message = "Por favor informar um número inteiro positivo")
	private int duracao;
	
	@NotBlank(message = "Campo requerido")
	private String capaURL;
	
	@NotBlank(message = "A data do produto não pode ser futura")
	private int anoLancamento;
	
	@NotEmpty(message = "Produto sem categoria não é permitido")
	private List<CategoriaDTO> categories = new ArrayList<>();
	
	public FilmeDTO(Long id, String titulo, String sinopse, int duracao, String capaURL, int anoLancamento) {
		this.id = id;
		this.titulo = titulo;
		this.sinopse = sinopse;
		this.duracao = duracao;
		this.capaURL = capaURL;
		this.anoLancamento = anoLancamento;
	}

  public FilmeDTO(Filme entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.sinopse = entity.getSinopse();
		this.duracao = entity.getDuracao();
		this.capaURL = entity.getCapaURL();
		this.anoLancamento = entity.getAnoLancamento();
	}

	public FilmeDTO(Filme entity, Set<Categoria> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoriaDTO(cat)));
	}
}
