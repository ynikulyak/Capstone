package com.order.service;

import com.order.domain.Customer;
import com.order.domain.LineItem;
import com.order.domain.LineItemRepository;
import com.order.domain.Order;
import com.order.domain.OrderAssignment;
import com.order.domain.OrderAssignmentRepository;
import com.order.domain.OrderAssignmentStatus;
import com.order.domain.OrderRepository;
import com.order.domain.OrderStatus;
import com.order.rest.CartItemDto;
import com.order.rest.CreateOrderRequest;
import com.order.rest.CreateOrderResponse;
import com.order.rest.OrderDto;
import com.order.rest.ProductOptionDto;
import com.order.rest.ProductSizeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private static final Logger log = LoggerFactory.getLogger(OrderService.class);

  private final OrderRepository orderRepository;
  private final OrderAssignmentRepository orderAssignmentRepository;
  private final LineItemRepository lineItemRepository;
  private final ProductsAndCategoriesService productsAndCategoriesService;
  private final CustomerService customerService;

  public OrderService(OrderRepository orderRepository, OrderAssignmentRepository orderAssignmentRepository,
                      LineItemRepository lineItemRepository,
                      ProductsAndCategoriesService productsAndCategoriesService, CustomerService customerService) {
    this.orderRepository = orderRepository;
    this.orderAssignmentRepository = orderAssignmentRepository;
    this.lineItemRepository = lineItemRepository;
    this.productsAndCategoriesService = productsAndCategoriesService;
    this.customerService = customerService;
  }

  public Optional<OrderDto> getById(Long id) {
    Optional<Order> result = orderRepository.findById(id);
    if (!result.isPresent()) {
      return Optional.empty();
    }
    Order order = result.get();

    Collection<Long> sizeIds = order.getLineItems().stream().map(LineItem::getProductSizeId)
        .collect(Collectors.toList());
    Map<Long, ProductSizeDto> mappedSizes = productsAndCategoriesService.getProductSizes(sizeIds)
        .stream().collect(Collectors.toMap(ProductSizeDto::getId, Function.identity()));

    Collection<Long> optionIds = order.getLineItems().stream().flatMap(lineItem -> lineItem.getOptionIds().stream())
        .collect(Collectors.toSet());
    Map<Long, ProductOptionDto> mappedOptions = productsAndCategoriesService.getProductOptions(optionIds)
        .stream().collect(Collectors.toMap(ProductOptionDto::getId, Function.identity()));

    OrderDto orderDto = OrderDto.from(order, mappedSizes::get, mappedOptions::get);
    return Optional.of(orderDto);
  }

  @Transactional
  public CreateOrderResponse create(CreateOrderRequest request) {
    Order order = new Order();
    order.setStatus(OrderStatus.PAID_AND_SCHEDULED.name());

    order.setTax(request.tax);
    order.setTotal(request.total);
    order.setCreated(new Date());
    order.setReady(new Date(0));
    order.setPickedUp(new Date(0));

    Customer customer = customerService.createCustomer(request.getPaymentData());
    order.setCustomer(customer);

    log.info("Saving order for customer: " + customer.getId());
    orderRepository.save(order);

    List<LineItem> items = new ArrayList<>(request.getCartItems().size());
    for (CartItemDto cartItem : request.getCartItems()) {
      LineItem lineItem = cartItem.toLineItem();
      lineItem.setOrder(order);
      items.add(lineItem);
    }
    order.setLineItems(items);
    lineItemRepository.saveAll(items);

    OrderAssignment orderAssignment = new OrderAssignment();
    orderAssignment.setCreated(new Date());
    orderAssignment.setOrder(order);
    orderAssignment.setStatus(OrderAssignmentStatus.UNASSIGNED.name());
    orderAssignmentRepository.save(orderAssignment);

    lineItemRepository.flush();
    orderRepository.flush();
    orderAssignmentRepository.flush();

    log.info("Saved order id: " + order.getId());
    return new CreateOrderResponse().setOrderId(order.getId());
  }
}
