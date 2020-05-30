package com.order.rest;

import com.order.domain.Customer;

import java.util.Date;

public class CustomerDto {
  public Long id;

  public String firstName;
  public String lastName;
  public String email;
  public String phone;
  public String username;
  public Date created;

  public static CustomerDto from(Customer customer) {
    CustomerDto dto = new CustomerDto();

    dto.id = customer.getId();
    dto.created = customer.getCreated();

    dto.email = customer.getEmail();
    dto.username = customer.getUsername();
    dto.phone = customer.getPhone();

    dto.firstName = customer.getFirstName();
    dto.lastName = customer.getLastName();

    return dto;
  }
}
