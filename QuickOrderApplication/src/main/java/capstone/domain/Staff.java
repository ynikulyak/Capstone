package capstone.domain;

import java.util.Date;

/**
 * Staff DTO.
 */
public class Staff {
  public Long id;

  public String firstName;

  public String lastName;

  public String email;

  public String phone;

  public Date created;

  public String getName() {
    return firstName + " " + lastName;
  }
}
