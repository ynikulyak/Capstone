package com.order.service;

import com.order.domain.OrderAssignment;
import com.order.domain.OrderAssignmentRepository;
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
import java.util.List;
import java.util.Optional;

/**
 * Order assignment service.
 */
@Service
public class OrderAssignmentService {

  private static final Logger log = LoggerFactory.getLogger(OrderAssignmentService.class);

  private final OrderService orderService;
  private final OrderAssignmentRepository orderAssignmentRepository;

  public OrderAssignmentService(OrderService orderService, OrderAssignmentRepository orderAssignmentRepository) {
    this.orderService = orderService;
    this.orderAssignmentRepository = orderAssignmentRepository;
  }

  @Transactional
  public Page<OrderAssignmentDto> findActiveByStaffId(long staffId, int page, int size) {
    return toDtoPage(orderAssignmentRepository.findActiveByStaffId(staffId, PageRequest.of(page, size)));
  }

  public Page<OrderAssignmentDto> findAllUnassigned(int page, int size) {
    return toDtoPage(orderAssignmentRepository.findAllUnassigned(PageRequest.of(page, size)));
  }

  private Page<OrderAssignmentDto> toDtoPage(Page<OrderAssignment> entities) {
    List<OrderAssignmentDto> dtoList = new ArrayList<>(entities.getContent().size());
    for (OrderAssignment orderAssignment : entities.getContent()) {
      Optional<OrderDto> orderDto = orderService.getById(orderAssignment.getOrder().getId());
      if (!orderDto.isPresent()) {
        log.warn("Can't find order data for: " + orderAssignment.getOrder().getId());
        continue;
      }
      dtoList.add(OrderAssignmentDto.from(orderAssignment, orderDto.get()));
    }
    return new PageImpl<>(dtoList, entities.getPageable(), entities.getTotalElements());
  }
}
