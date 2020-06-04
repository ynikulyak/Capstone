package com.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {

  @Query("select oa from OrderAssignment oa where oa.staff.id = :staffId and (oa.status = 'ASSIGNED' or oa.status = 'PROCESSING') order by oa.created")
  Page<OrderAssignment> findActiveByStaffId(@PathVariable("staffId") long staffId, Pageable pageable);

  @Query("select oa from OrderAssignment oa where oa.staff IS NULL and oa.status = 'UNASSIGNED' order by oa.created")
  Page<OrderAssignment> findAllUnassigned(Pageable pageable);

  // By order, expected 1 assignment for 1 order.
  @Query("select oa from OrderAssignment oa where oa.order.id = :orderId and (oa.staff IS NULL and oa.status = 'UNASSIGNED')")
  List<OrderAssignment> findActiveByOrderId(@PathVariable("orderId") long orderId);
}
