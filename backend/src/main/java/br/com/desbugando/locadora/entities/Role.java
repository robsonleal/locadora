package br.com.desbugando.locadora.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "TB_ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OID_ROLE")
	private Long id;

  @Column(name = "TXT_AUTHORITY")
	private String authority;
	
	public Role(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}
}
