package com.order.rest;

import com.order.domain.Customer;

import java.util.Date;
import java.util.Optional;

/**
 * Payment data.
 */
public class PaymentDataDto {
  public String firstName;
  public String lastName;
  public String phone;
  public String email;
  public String username;
  public String password;

  // Billing address (for logging purposes)
  public String address1;
  public String address2;
  public String city;
  public String state;
  public String country;
  public String zipCode;

  // Card data (for logging purposes)
  public String ccName;
  public String ccExpiration;

  // Getters and setters are needed for Spring to populate data from POST
  public String getFirstName() {
    return nullToTrimmed(firstName);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return nullToTrimmed(lastName);
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return nullToTrimmedLowerCase(email);
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return nullToTrimmedLowerCase(username);
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return nullToTrimmed(password);
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress1() {
    return nullToTrimmed(address1);
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return nullToTrimmed(address2);
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return nullToTrimmed(city);
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return nullToTrimmed(state);
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCcExpiration() {
    return nullToTrimmed(ccExpiration);
  }

  public void setCcExpiration(String ccExpiration) {
    this.ccExpiration = ccExpiration;
  }

  public String getCountry() {
    return nullToTrimmed(country);
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipCode() {
    return nullToTrimmed(zipCode);
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCcName() {
    return nullToTrimmed(ccName);
  }

  public void setCcName(String ccName) {
    this.ccName = ccName;
  }


  public String getPhone() {
    return nullToTrimmedLowerCase(phone);
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Customer toCustomer() {
    Customer customer = new Customer();
    customer.setCreated(new Date());
    customer.setEmail(getEmail() != null ? (getEmail().toLowerCase()) : "");
    customer.setFirstName(getFirstName());
    customer.setLastName(getLastName());
    customer.setPhone(getPhone());
    customer.setUsername(getUsername());
    return customer;
  }

  public Optional<String> validate() {
    if (isNullOrEmpty(firstName)) {
      return Optional.of("First name in payment data is empty");
    }
    if (isNullOrEmpty(lastName)) {
      return Optional.of("Last name in payment data is empty");
    }
    if (isNullOrEmpty(phone)) {
      return Optional.of("Phone in payment data is empty");
    }
    if (isNullOrEmpty(email)) {
      return Optional.of("Email in payment data is empty");
    }
    if (!isNullOrEmpty(username) && isNullOrEmpty(password)) {
      return Optional.of("Password in payment data is empty");
    }
    return Optional.empty();
  }

  private static boolean isNullOrEmpty(String s) {
    return s == null || s.trim().isEmpty();
  }

  private static String nullToTrimmed(String s) {
    return s != null ? s.trim() : "";
  }

  private static String nullToTrimmedLowerCase(String s) {
    return s != null ? s.trim().toLowerCase() : "";
  }
}
