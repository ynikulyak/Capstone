package capstone.domain;

/**
 * Payment data.
 */
public class PaymentData {
  public String firstName;
  public String lastName;
  public String email;
  public String phone;
  public String username;
  public String password;

  // Billing address
  public String address1;
  public String address2;
  public String city;
  public String state;
  public String country;
  public String zipCode;

  // Card data
  public String ccName;
  public String ccNumber;
  public String ccExpiration;
  public String ccCvv;

  // Getters and setters are needed for Spring to populate data from POST
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCcName() {
    return ccName;
  }

  public void setCcName(String ccName) {
    this.ccName = ccName;
  }

  public String getCcNumber() {
    return ccNumber;
  }

  public void setCcNumber(String ccNumber) {
    this.ccNumber = ccNumber;
  }

  public String getCcExpiration() {
    return ccExpiration;
  }

  public void setCcExpiration(String ccExpiration) {
    this.ccExpiration = ccExpiration;
  }

  public String getCcCvv() {
    return ccCvv;
  }

  public void setCcCvv(String ccCvv) {
    this.ccCvv = ccCvv;
  }
}
