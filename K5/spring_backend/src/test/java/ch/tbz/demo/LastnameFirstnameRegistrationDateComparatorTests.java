package ch.tbz.demo;

import ch.tbz.demo.domain.addressbook.Addressbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

class LastnameFirstnameRegistrationDateComparatorTests {

    @Test
    void testComparator() {
        Addressbook addressbook1 = new Addressbook(UUID.randomUUID(), "Hans", "Auster", "mike@mail.com", "123");
        Addressbook addressbook2 = new Addressbook(UUID.randomUUID(), "Bepp", "Zam", "sepp@ham.com", "123");
        Addressbook addressbook3 = new Addressbook(UUID.randomUUID(), "John", "Zazo", "john@cazo.com", "123");

        List<Addressbook> addresses = Arrays.asList(addressbook1, addressbook2, addressbook3);

        Assertions.assertEquals(addressbook3, addresses.get(2)); //John Zazo
        Assertions.assertEquals(addressbook2, addresses.get(1)); // Bepp Zam
        Assertions.assertEquals(addressbook1, addresses.get(0)); //Hans Auster

    }
}
