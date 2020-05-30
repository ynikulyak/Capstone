package com.order.rest;

import com.order.domain.Order;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Order DTO.
 */
public class OrderDto {

  public Long id;

  public List<OrderLineItemDto> items;

  public CustomerDto customer;

  public double tax;

  public double total;

  public String status;

  public Date created;

  public Date ready;

  public Date pickedUp;

  public static OrderDto from(
      Order order, Function<Long, ProductSizeDto> sizeProvider,
      Function<Long, ProductOptionDto> optionProvider) {
    OrderDto dto = new OrderDto();
    dto.id = order.getId();

    dto.total = order.getTotal();
    dto.tax = order.getTax();
    dto.status = order.getStatus();

    dto.pickedUp = order.getPickedUp();
    dto.created = order.getCreated();
    dto.ready = order.getReady();

    dto.customer = CustomerDto.from(order.getCustomer());
    dto.items = OrderLineItemDto.from(order.getLineItems(), sizeProvider, optionProvider);

    return dto;
  }
}
