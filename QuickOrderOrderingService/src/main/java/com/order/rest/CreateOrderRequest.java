package com.order.rest;

import java.util.List;
import java.util.Optional;

/**
 * Create order request.
 */
public class CreateOrderRequest {
  public List<CartItemDto> cartItems;
  public PaymentDataDto paymentData;

  public double tax;
  public double total;

  // Getters and setters for or Spring to parse.
  public List<CartItemDto> getCartItems() {
    return cartItems;
  }

  public void setCartItems(List<CartItemDto> cartItems) {
    this.cartItems = cartItems;
  }

  public PaymentDataDto getPaymentData() {
    return paymentData;
  }

  public void setPaymentData(PaymentDataDto paymentData) {
    this.paymentData = paymentData;
  }

  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Optional<String> validate() {
    if (paymentData == null ||  cartItems == null || cartItems.isEmpty()) {
      return Optional.of("Payment data or cart items are empty.");
    }
    if (tax < 0.01d || total < 0.01d || total < tax) {
      return Optional.of("Incorrect total or tax.");
    }
    for (CartItemDto cartItemDto : cartItems) {
      Optional<String> cartItemError = cartItemDto.validate();
      if (cartItemError.isPresent()) {
        return cartItemError;
      }
    }
    return paymentData.validate();
  }
}
