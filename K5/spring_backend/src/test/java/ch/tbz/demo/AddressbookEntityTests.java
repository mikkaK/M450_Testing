package ch.tbz.demo;

import ch.tbz.demo.domain.addressbook.Addressbook;
import ch.tbz.demo.domain.addressbook.AddressbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AddressbookEntityTests {

    @Autowired
    private AddressbookRepository repository;

    @Test
    public void testEntitySaveAndRetrieve() {
        Addressbook entity = new Addressbook();
        entity = repository.save(entity);

        Addressbook retrievedEntity = repository.findById(entity.getId()).orElse(null);
        assertNotNull(retrievedEntity);
    }
}
