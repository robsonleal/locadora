package com.desbugando.catalogo.dtos;

import javax.validation.constraints.NotBlank;

public class UserInsertDto extends UserDto {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Campo obrigatório")
    private String password;

    public UserInsertDto() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
