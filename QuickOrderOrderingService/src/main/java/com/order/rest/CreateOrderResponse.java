package com.order.rest;

/**
 * Create order response that contains created order id.
 */
public class CreateOrderResponse {
  public long orderId;

  // Getters and setters for or Spring to parse.
  public long getOrderId() {
    return orderId;
  }

  public CreateOrderResponse setOrderId(long orderId) {
    this.orderId = orderId;
    return this;
  }
}
