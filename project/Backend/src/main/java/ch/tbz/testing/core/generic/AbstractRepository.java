package ch.tbz.testing.core.generic;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {
}
