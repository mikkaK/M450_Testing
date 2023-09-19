package ch.bbw.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceTests {

    AddressService addressService;
    @BeforeEach
    void setup() {
        AddressDAO addressDAO = new AddressDAO_Memory();
        addressService = new AddressService(addressDAO);
    }

    @Test
    void testCreateAddress() {

    }
}
