package com.order.controller;

import java.util.Optional;

import com.order.rest.CreateOrderRequest;
import com.order.rest.CreateOrderResponse;
import com.order.rest.OrderDto;
import com.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class OrderRestController {

  private static final Logger log = LoggerFactory.getLogger(OrderRestController.class);

  @Autowired
  private OrderService orderService;

  @GetMapping("/api/orders/v1/{id}")
  public OrderDto getOrder(@PathVariable("id") String id) {
    Optional<OrderDto> order = orderService.getById(Long.parseLong(id));
    // return order object in form of JSON if object present
    if (order.isPresent()) {
      return order.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order was not found: " + id);
  }

  @PostMapping(path = "/api/order/v1/create", consumes = "application/json", produces = "application/json")
  public CreateOrderResponse create(@RequestBody CreateOrderRequest createOrderRequest) {
    log.info("Checking parsed request:" + createOrderRequest);
    if (createOrderRequest == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Optional<String> error = createOrderRequest.validate();
    if (error.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.get());
    }
    return orderService.create(createOrderRequest);
  }
}
