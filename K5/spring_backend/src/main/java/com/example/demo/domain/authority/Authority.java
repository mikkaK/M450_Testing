package com.example.demo.domain.authority;

import com.example.demo.core.generic.AbstractEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority extends AbstractEntity {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public Authority() {
  }

  public Authority(UUID id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Authority setName(String name) {
    this.name = name;
    return this;
  }
}
