package com.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

  @Query("select c from Staff c where c.email = :email")
  List<Staff> findByEmail(@PathVariable("email") String email);

  @Query("select c from Staff c where c.email = :email and c.passwordHash = :passwordHash")
  List<Staff> findByEmailAndPassword(@PathVariable("email") String email, @PathVariable("passwordHash") String passwordHash);
}
