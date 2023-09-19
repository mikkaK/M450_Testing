package com.example.demo.domain.user;

import com.example.demo.core.generic.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {


  @Autowired
  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }


  @Override
  public User register(User user) {
    return save(user);
  }
  @Override
  public User registerUser(User user){
    user.setPassword(getRandomSpecialChars(20).toString());
    return save(user);
  }

  public Stream<Character> getRandomSpecialChars(int count) {
    Random random = new SecureRandom();
    IntStream specialChars = random.ints(count, 33, 45);
    return specialChars.mapToObj(data -> (char) data);
  }

}
