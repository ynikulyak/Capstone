package com.order.rest;

import com.order.domain.LineItem;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Container for cart item identifiers.
 */
public class CartItemDto {

  public long productId;
  public long productSizeId;
  public int quantity;
  public Set<Long> optionIds;
  public double itemPrice;

  // Getters and setters for or Spring to parse.
  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getProductSizeId() {
    return productSizeId;
  }

  public void setProductSizeId(long productSizeId) {
    this.productSizeId = productSizeId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Set<Long> getOptionIds() {
    return optionIds != null ? optionIds : Collections.emptySet();
  }

  public void setOptionIds(Set<Long> optionIds) {
    this.optionIds = optionIds;
  }

  public double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }

  public LineItem toLineItem() {
    LineItem lineItem = new LineItem();
    lineItem.setQuantity(getQuantity());
    lineItem.setTotalPrice(getItemPrice());
    lineItem.setProductSizeId(getProductSizeId());
    lineItem.setAttribute_value_ids(getOptionIds().stream()
        .map(Number::toString).collect(Collectors.joining(",")));
    return lineItem;
  }

  public Optional<String> validate() {
    if (productId <= 0L) {
      return Optional.of("Bad product id in line item: " + productId);
    }
    if (productSizeId <= 0L) {
      return Optional.of("Bad product size id in line item: " + productSizeId);
    }
    if (quantity < 1) {
      return Optional.of("Bad quantity in line item: " + quantity);
    }
    if (itemPrice <= 0.01D) {
      return Optional.of("Wrong price");
    }
    return Optional.empty();
  }
}
