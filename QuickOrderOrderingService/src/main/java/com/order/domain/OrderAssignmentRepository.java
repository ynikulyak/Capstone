package com.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {

  // By staff id
  @Query("select COUNT(oa) from OrderAssignment oa where oa.staff.id = :staffId and (oa.status = 'ASSIGNED' or oa.status = 'PROCESSING')")
  long countActiveByStaffId(@PathVariable("staffId") long staffId);

  @Query("select oa from OrderAssignment oa where oa.staff.id = :staffId and (oa.status = 'ASSIGNED' or oa.status = 'PROCESSING') order by oa.created")
  Page<OrderAssignment> findActiveByStaffId(@PathVariable("staffId") long staffId, Pageable pageable);

  // Unassigned
  @Query("select COUNT(oa) from OrderAssignment oa where oa.staff IS NULL and oa.status = 'UNASSIGNED'")
  long countAllUnassigned();

  @Query("select oa from OrderAssignment oa where oa.staff IS NULL and oa.status = 'UNASSIGNED' order by oa.created")
  Page<OrderAssignment> findAllUnassigned(Pageable pageable);
}
