package ch.letterix.coverletterservice.core.generic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {
}
