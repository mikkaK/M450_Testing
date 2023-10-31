package ch.tbz.demo.domain.addressbook;

import ch.tbz.demo.core.generic.AbstractService;

public interface AddressbookService extends AbstractService<Addressbook> {
  Addressbook register(Addressbook addressbook);

}
