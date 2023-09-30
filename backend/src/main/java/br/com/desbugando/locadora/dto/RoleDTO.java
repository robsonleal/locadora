package br.com.desbugando.locadora.dto;

import br.com.desbugando.locadora.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	private Long id;
	private String authority;
	
	public RoleDTO(Role role) {
		id = role.getId();
		authority = role.getAuthority();
	}
}
