package com.desbugando.catalogo.dtos;

import com.desbugando.catalogo.entities.Role;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    private String authority;

    public RoleDto() {
    }

    public RoleDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDto(Role entity) {
        this.setId(entity.getId());
        this.setAuthority(entity.getAuthority());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
