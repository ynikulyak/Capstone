package com.order.rest;

import com.order.domain.Staff;

import java.util.Date;

public class StaffDto {
  public Long id;
  public String firstName;
  public String lastName;
  public String email;
  public String phone;
  public Date created;

  public static StaffDto from(Staff staff) {
    StaffDto dto = new StaffDto();
    dto.email = staff.getEmail();
    dto.phone = staff.getPhone();
    dto.firstName = staff.getFirstName();
    dto.lastName = staff.getLastName();
    dto.id = staff.getId();
    dto.created = staff.getCreated();
    return dto;
  }
}
