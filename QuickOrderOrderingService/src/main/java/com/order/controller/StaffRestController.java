package com.order.controller;

import com.order.domain.Staff;
import com.order.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class StaffRestController {

  private final StaffService staffService;

  public StaffRestController(StaffService staffService) {
    this.staffService = staffService;
  }

  @GetMapping("/api/staff/v1/{id}")
  public Staff getOrder(@PathVariable("id") String id) {
    Optional<Staff> staff = staffService.getById(Long.parseLong(id));
    // return order object in form of JSON if object present
    if (staff.isPresent()) {
      Staff result = staff.get();
      result.setPasswordHash(null);
      return result;
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff was not found: " + id);
  }

  @PostMapping(path = "/api/staff-op/v1/create", consumes = "application/json", produces = "application/json")
  public Map<String, Long> create(@RequestBody Staff staff) {
    if (staff.getFirstName() == null || staff.getLastName() == null || staff.getEmail() == null
        || staff.getPhone() == null || staff.getPasswordHash() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff member can't be created.");
    }
    long staffId = staffService.create(staff, staff.getPasswordHash());
    Map<String, Long> result = new HashMap<>();
    result.put("staffId", staffId);
    return result;
  }
}
