package com.example.demo.core.generic;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
  private UUID id;

  protected AbstractEntity() {
  }

  protected AbstractEntity(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public AbstractEntity setId(UUID id) {
    this.id = id;
    return this;
  }
}