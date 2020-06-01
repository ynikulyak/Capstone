package com.order.rest;

import com.order.domain.OrderAssignment;

import java.util.Date;

public class OrderAssignmentDto {

  public Long id;

  public StaffDto staff;

  public OrderDto order;

  public String status;

  public Date created;

  public static OrderAssignmentDto from(OrderAssignment assignment, OrderDto orderDto) {
    OrderAssignmentDto dto = new OrderAssignmentDto();
    dto.id = assignment.getId();
    dto.created = assignment.getCreated();
    dto.status = assignment.getStatus();
    dto.staff = assignment.getStaff() != null ? StaffDto.from(assignment.getStaff()) : null;
    dto.order = orderDto;
    return dto;
  }
}
