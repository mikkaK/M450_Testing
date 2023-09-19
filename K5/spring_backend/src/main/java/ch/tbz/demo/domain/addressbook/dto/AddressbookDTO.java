package ch.tbz.demo.domain.addressbook.dto;

import ch.tbz.demo.core.generic.AbstractDTO;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Email;

public class AddressbookDTO extends AbstractDTO {

  private String firstName;

  private String lastName;

  @Email
  private String email;

  public AddressbookDTO() {
  }

  public AddressbookDTO(UUID id, String firstName, String lastName, String email) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public AddressbookDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public AddressbookDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public AddressbookDTO setEmail(String email) {
    this.email = email;
    return this;
  }

}
