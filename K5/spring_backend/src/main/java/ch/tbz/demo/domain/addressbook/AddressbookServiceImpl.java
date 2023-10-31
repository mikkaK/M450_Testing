package ch.tbz.demo.domain.addressbook;

import ch.tbz.demo.core.generic.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class AddressbookServiceImpl extends AbstractServiceImpl<Addressbook> implements AddressbookService {


  @Autowired
  public AddressbookServiceImpl(AddressbookRepository repository) {
    super(repository);
  }


  @Override
  public Addressbook register(Addressbook addressbook) {
    return save(addressbook);
  }



}
