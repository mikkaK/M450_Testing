package com.example.demo.core.security.helpers;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Credentials {

  private String email;
  private String password;


  public String getEmail() {
    return email;
  }

  public Credentials setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Credentials setPassword(String password) {
    this.password = password;
    return this;
  }
}
