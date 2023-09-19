package ch.tbz.demo.domain.addressbook;

import ch.tbz.demo.core.generic.AbstractRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressbookRepository extends AbstractRepository<Addressbook> {
  Optional<Addressbook> findByEmail(String email);
}
