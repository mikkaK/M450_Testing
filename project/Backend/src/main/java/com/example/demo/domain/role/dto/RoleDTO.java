package com.example.demo.domain.role.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.authority.dto.AuthorityDTO;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleDTO extends AbstractDTO {

  @NotNull
  @Size(min = 1, max = 255)
  private String name;

  @Valid
  private Set<AuthorityDTO> authorities;

  public RoleDTO() {
  }

  public RoleDTO(UUID id, String name, Set<AuthorityDTO> authorities) {
    super(id);
    this.name = name;
    this.authorities = authorities;
  }

  public String getName() {
    return name;
  }

  public RoleDTO setName(String name) {
    this.name = name;
    return this;
  }

  public Set<AuthorityDTO> getAuthorities() {
    return authorities;
  }

  public RoleDTO setAuthorities(Set<AuthorityDTO> authorities) {
    this.authorities = authorities;
    return this;
  }
}