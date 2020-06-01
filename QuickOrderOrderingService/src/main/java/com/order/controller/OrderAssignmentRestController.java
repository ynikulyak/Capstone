package com.order.controller;

import com.order.domain.OrderAssignment;
import com.order.rest.OrderAssignmentDto;
import com.order.service.OrderAssignmentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderAssignmentRestController {

  private final OrderAssignmentService orderAssignmentService;

  public OrderAssignmentRestController(OrderAssignmentService orderAssignmentService) {
    this.orderAssignmentService = orderAssignmentService;
  }

  @GetMapping("/api/assignments/v1/active/{staffId}")
  public Page<OrderAssignmentDto> getActiveAssignments(
      @PathVariable("staffId") long staffId,
      @RequestParam("page") int page, @RequestParam("size") int size) {
    return orderAssignmentService.findActiveByStaffId(staffId,
        page >= 0 ? page : 0, size > 0 ? size : 10);
  }

  @GetMapping("/api/assignments/v1/unassigned/")
  public Page<OrderAssignmentDto> getUnassignedAssignments(
      @RequestParam("page") int page, @RequestParam("size") int size) {
    return orderAssignmentService.findAllUnassigned(page >= 0 ? page : 0, size > 0 ? size : 10);
  }
}
