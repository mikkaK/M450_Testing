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
  @Override
  public Addressbook registerUser(Addressbook addressbook){
    addressbook.setPassword(getRandomSpecialChars(20).toString());
    return save(addressbook);
  }

  public Stream<Character> getRandomSpecialChars(int count) {
    Random random = new SecureRandom();
    IntStream specialChars = random.ints(count, 33, 45);
    return specialChars.mapToObj(data -> (char) data);
  }

}
