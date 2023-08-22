package ch.letterix.coverletterservice.core.generic;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

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