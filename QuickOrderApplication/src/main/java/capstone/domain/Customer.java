package capstone.domain;

/**
 * Customer DTO
 */
public class Customer {
  public Long id;

  public String firstName;

  public String lastName;

  public String email;

  public String phone;

  public String username;

  public String getName() {
    return firstName + " " + lastName;
  }
}
