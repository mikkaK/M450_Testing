package ch.letterix.coverletterservice.core.generic;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface AbstractService<T extends AbstractEntity> {

  T save(T entity);

  void deleteById(UUID id) throws NoSuchElementException;

  T updateById(UUID id, T entity) throws NoSuchElementException;

  List<T> findAll();

  List<T> findAll(Pageable pageable);

  T findById(UUID id);

  boolean existsById(UUID id);

}
