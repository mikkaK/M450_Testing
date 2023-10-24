package com.example.demo.core.security.permissionevaluators;

import com.example.demo.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserPermissionEvaluator {

  public UserPermissionEvaluator() {
  }

  public boolean isUserAboveAge(User principal, int age) {
    return true;
  }

  public boolean isOwnProfile(User principal, UUID id){
    return principal.getId().equals(id);
  }

}
