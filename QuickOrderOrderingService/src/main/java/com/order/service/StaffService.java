package com.order.service;

import com.order.domain.Staff;
import com.order.domain.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
  private final StaffRepository staffRepository;
  private final HashingService hashingService;

  public StaffService(StaffRepository staffRepository, HashingService hashingService) {
    this.staffRepository = staffRepository;
    this.hashingService = hashingService;
  }

  @Transactional
  public Optional<Staff> getById(long id) {
    return staffRepository.findById(id);
  }

  @Transactional
  public long create(Staff staff, String password) {
    List<Staff> existing = staffRepository.findByEmail(staff.getEmail().toLowerCase());
    if (!existing.isEmpty()) {
      throw new IllegalArgumentException("Staff exists for " + staff.getEmail());
    }
    staff.setId(null);
    staff.setEmail(staff.getEmail().toLowerCase());
    staff.setCreated(new Date());
    staff.setPasswordHash(hashingService.hash(password));
    staffRepository.saveAndFlush(staff);
    return staff.getId();
  }
}
