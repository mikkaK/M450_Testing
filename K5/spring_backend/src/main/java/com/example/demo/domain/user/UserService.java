package com.example.demo.domain.user;

import com.example.demo.core.generic.AbstractService;

public interface UserService extends AbstractService<User> {
  User register(User user);

  User registerUser(User user);
}
