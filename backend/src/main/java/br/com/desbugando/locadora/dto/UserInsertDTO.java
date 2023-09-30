package br.com.desbugando.locadora.dto;

import br.com.desbugando.locadora.service.validation.UserInsertValid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO {

	private String password;

	UserInsertDTO() {
		super();
	}
}
