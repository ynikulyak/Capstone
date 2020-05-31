package com.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Repository
public interface OrderAssignmentRepository extends JpaRepository<OrderAssignment, Long> {

  /*
  // By staff id
  @Query("select COUNT(oa) from OrderAssignment oa where oa.staffId = :staffId and (oa.status == 'ASSIGNED' or oa.status == 'PROCESSING')")
  long countActiveByStaffId(@PathVariable("staffId") long staffId);

  @Query("select oa from OrderAssignment oa where oa.staffId = :staffId and (oa.status == 'ASSIGNED' or oa.status == 'PROCESSING') order by oa.created LIMIT :from, :size")
  List<OrderAssignment> findActiveByStaffId(@PathVariable("staffId") long staffId,
                                            @PathVariable("from") long from, @PathVariable("size") long size);

  default Page<OrderAssignment> findActiveUnassigned(long staffId, Pageable pageable) {
    long total = countActiveByStaffId(staffId);
    if (pageable.getOffset() < 0 || pageable.getOffset() > total) {
      return new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 0), 0);
    }
    List<OrderAssignment> resultList = findActiveByStaffId(staffId, pageable.getOffset(), pageable.getPageSize());
    return new PageImpl<>(resultList, pageable, total);
  }

  // Unassigned
  @Query("select COUNT(oa) from OrderAssignment oa where oa.staffId IS NULL and oa.status == 'UNASSIGNED'")
  long countAllUnassigned();

  @Query("select oa from OrderAssignment oa where oa.staffId IS NULL and oa.status == 'UNASSIGNED' order by oa.created LIMIT :from, :size")
  List<OrderAssignment> findAllUnassigned(@PathVariable("from") long from, @PathVariable("size") long size);

  default Page<OrderAssignment> findAllUnassigned(Pageable pageable) {
    long total = countAllUnassigned();
    if (pageable.getOffset() < 0 || pageable.getOffset() > total) {
      return new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 0), 0);
    }
    List<OrderAssignment> resultList = findAllUnassigned(pageable.getOffset(), pageable.getPageSize());
    return new PageImpl<>(resultList, pageable, total);
  }
  */
}
