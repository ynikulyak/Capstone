package com.order.service;

import com.order.domain.OrderAssignment;
import com.order.domain.OrderAssignmentRepository;
import com.order.domain.OrderAssignmentStatus;
import com.order.domain.OrderRepository;
import com.order.domain.OrderStatus;
import com.order.domain.Staff;
import com.order.rest.OrderAssignmentDto;
import com.order.rest.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Order assignment service.
 */
@Service
public class OrderAssignmentService {

  private static final Logger log = LoggerFactory.getLogger(OrderAssignmentService.class);

  private final OrderService orderService;
  private final StaffService staffService;
  private final OrderRepository orderRepository;
  private final OrderAssignmentRepository orderAssignmentRepository;

  public OrderAssignmentService(OrderService orderService,
                                StaffService staffService,
                                OrderRepository orderRepository,
                                OrderAssignmentRepository orderAssignmentRepository) {
    this.orderService = orderService;
    this.staffService = staffService;
    this.orderRepository = orderRepository;
    this.orderAssignmentRepository = orderAssignmentRepository;
  }

  public Optional<OrderAssignmentDto> get(long orderIdAssignmentId) {
    return orderAssignmentRepository.findById(orderIdAssignmentId).map(this::toDto);
  }

  @Transactional
  public Page<OrderAssignmentDto> findActiveByStaffId(long staffId, int page, int size) {
    return toDtoPage(orderAssignmentRepository.findActiveByStaffId(staffId, PageRequest.of(page, size)));
  }

  @Transactional
  public Page<OrderAssignmentDto> findAllUnassigned(int page, int size) {
    return toDtoPage(orderAssignmentRepository.findAllUnassigned(PageRequest.of(page, size)));
  }

  @Transactional
  public Optional<OrderAssignmentDto> assignOrder(long staffId, long orderId) {
    List<OrderAssignment> orderAssignments = orderAssignmentRepository.findActiveByOrderId(orderId);
    if (orderAssignments.isEmpty()) {
      log.info("There is no order found for " + orderId);
      return Optional.empty();
    }
    Optional<Staff> staff = staffService.getById(staffId);
    if (!staff.isPresent()) {
      log.info("There is no staff found for " + staffId);
      return Optional.empty();
    }
    OrderAssignment orderAssignment = orderAssignments.stream().findFirst().get();
    orderAssignment.setStaff(staff.get());
    orderAssignment.setStatus(OrderAssignmentStatus.ASSIGNED.name());
    orderAssignment.getOrder().setStatus(OrderStatus.ASSIGNED.name());
    orderAssignmentRepository.save(orderAssignment);
    orderRepository.save(orderAssignment.getOrder());
    orderAssignmentRepository.flush();
    orderRepository.flush();
    return Optional.of(toDto(orderAssignment));
  }

  @Transactional
  public Optional<OrderAssignmentDto> cancelOrder(long orderAssignmentId) {
    return updateStatus(orderAssignmentId, OrderAssignmentStatus.CANCELLED.name(),
        OrderStatus.CANCELLED.name());
  }

  @Transactional
  public Optional<OrderAssignmentDto> startOrder(long orderAssignmentId) {
    return updateStatus(orderAssignmentId, OrderAssignmentStatus.PROCESSING.name(),
        OrderStatus.PROCESSING.name());
  }

  @Transactional
  public Optional<OrderAssignmentDto> completeOrder(long orderAssignmentId) {
    return updateStatus(orderAssignmentId, OrderAssignmentStatus.COMPLETED.name(),
        OrderStatus.COMPLETED.name());
  }

  @Transactional
  public Optional<OrderAssignmentDto> pickUpOrder(long orderAssignmentId) {
    Optional<OrderAssignment> orderAssignments = orderAssignmentRepository.findById(orderAssignmentId);
    if (!orderAssignments.isPresent()) {
      log.info("There is no order assignment found for " + orderAssignmentId);
      return Optional.empty();
    }
    OrderAssignment orderAssignment = orderAssignments.get();
    if (!OrderStatus.COMPLETED.name().equals(orderAssignment.getOrder().getStatus())) {
      log.info("Order assignment is not completed for " + orderAssignmentId);
      return Optional.empty();
    }
    orderAssignment.getOrder().setPickedUp(new Date());
    orderRepository.saveAndFlush(orderAssignment.getOrder());
    return Optional.of(toDto(orderAssignment));
  }

  private Optional<OrderAssignmentDto> updateStatus(long orderAssignmentId, String assignmentStatus, String orderStatus) {
    Optional<OrderAssignment> orderAssignments = orderAssignmentRepository.findById(orderAssignmentId);
    if (!orderAssignments.isPresent()) {
      log.info("There is no order assignment found for " + orderAssignmentId);
      return Optional.empty();
    }
    OrderAssignment orderAssignment = orderAssignments.get();
    orderAssignment.setStatus(assignmentStatus);
    orderAssignment.getOrder().setStatus(orderStatus);
    if (OrderStatus.COMPLETED.name().equals(orderStatus)) {
      orderAssignment.getOrder().setReady(new Date());
    }
    orderAssignmentRepository.save(orderAssignment);
    orderRepository.save(orderAssignment.getOrder());
    orderAssignmentRepository.flush();
    orderRepository.flush();
    return Optional.of(toDto(orderAssignment));
  }

  private Page<OrderAssignmentDto> toDtoPage(Page<OrderAssignment> entities) {
    List<OrderAssignmentDto> dtoList = new ArrayList<>(entities.getContent().size());
    for (OrderAssignment orderAssignment : entities.getContent()) {
      dtoList.add(toDto(orderAssignment));
    }
    return new PageImpl<>(dtoList, entities.getPageable(), entities.getTotalElements());
  }

  private OrderAssignmentDto toDto(OrderAssignment entity) {
    Optional<OrderDto> orderDto = orderService.getById(entity.getOrder().getId());
    return OrderAssignmentDto.from(entity, orderDto.get());
  }
}
