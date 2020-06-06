package com.order.controller;

import com.order.domain.OrderAssignment;
import com.order.rest.OrderAssignmentDto;
import com.order.service.OrderAssignmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class OrderAssignmentRestController {

  private final OrderAssignmentService orderAssignmentService;

  public OrderAssignmentRestController(OrderAssignmentService orderAssignmentService) {
    this.orderAssignmentService = orderAssignmentService;
  }

  @GetMapping("/api/assignments/v1/get/{orderIdAssignmentId}")
  public OrderAssignmentDto getAssignment(@PathVariable("orderIdAssignmentId") long orderIdAssignmentId) {
    Optional<OrderAssignmentDto> orderAssignmentDto = orderAssignmentService.get(orderIdAssignmentId);
    if (orderAssignmentDto.isPresent()) {
      return orderAssignmentDto.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Assigned order was not found for order assignment id: " + orderIdAssignmentId);
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

  @GetMapping("/api/assignments/v1/assign/{staffId}/{orderId}")
  public OrderAssignmentDto getAssign(
      @PathVariable("staffId") long staffId,
      @PathVariable("orderId") long orderId) {
    Optional<OrderAssignmentDto> orderAssignment =
        orderAssignmentService.assignOrder(staffId, orderId);
    if (orderAssignment.isPresent()) {
      return orderAssignment.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unassigned order was not found for order: " + orderId);
  }

  @GetMapping("/api/assignments/v1/start/{orderIdAssignmentId}")
  public OrderAssignmentDto getStart(
      @PathVariable("orderIdAssignmentId") long orderIdAssignmentId) {
    Optional<OrderAssignmentDto> orderAssignment =
        orderAssignmentService.startOrder(orderIdAssignmentId);
    if (orderAssignment.isPresent()) {
      return orderAssignment.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Assigned order was not found for order assignment id: " + orderIdAssignmentId);
  }

  @GetMapping("/api/assignments/v1/cancel/{orderIdAssignmentId}")
  public OrderAssignmentDto getCancel(
      @PathVariable("orderIdAssignmentId") long orderIdAssignmentId) {
    Optional<OrderAssignmentDto> orderAssignment =
        orderAssignmentService.cancelOrder(orderIdAssignmentId);
    if (orderAssignment.isPresent()) {
      return orderAssignment.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Order was not found for order assignment id: " + orderIdAssignmentId);
  }

  @GetMapping("/api/assignments/v1/complete/{orderIdAssignmentId}")
  public OrderAssignmentDto getComplete(
      @PathVariable("orderIdAssignmentId") long orderIdAssignmentId) {
    Optional<OrderAssignmentDto> orderAssignment =
        orderAssignmentService.completeOrder(orderIdAssignmentId);
    if (orderAssignment.isPresent()) {
      return orderAssignment.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Order was not found for order assignment id: " + orderIdAssignmentId);
  }

  @GetMapping("/api/assignments/v1/pickup/{orderIdAssignmentId}")
  public OrderAssignmentDto getPickUp(
      @PathVariable("orderIdAssignmentId") long orderIdAssignmentId) {
    Optional<OrderAssignmentDto> orderAssignment =
        orderAssignmentService.pickUpOrder(orderIdAssignmentId);
    if (orderAssignment.isPresent()) {
      return orderAssignment.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Assigned order was not found for order assignment id: " + orderIdAssignmentId);
  }
}
