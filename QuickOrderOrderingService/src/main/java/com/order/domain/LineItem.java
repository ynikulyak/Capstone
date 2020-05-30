package com.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Order line item entity
 */
@Entity
@Table(name = "Line_items")
public class LineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL generated value
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "product_size_id")
  private long productSizeId;

  @Column(name = "attribute_value_ids")
  private String attribute_value_ids;

  private int quantity;

  @Column(name = "total_price")
  private double totalPrice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public long getProductSizeId() {
    return productSizeId;
  }

  public void setProductSizeId(long productSizeId) {
    this.productSizeId = productSizeId;
  }

  public String getAttribute_value_ids() {
    return attribute_value_ids;
  }

  public void setAttribute_value_ids(String attribute_value_ids) {
    this.attribute_value_ids = attribute_value_ids;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Set<Long> getOptionIds() {
    String asAsString = getAttribute_value_ids();
    if (asAsString == null || asAsString.isEmpty()) {
      return Collections.emptySet();
    }
    Set<Long> result = new HashSet<>();
    for (String id : asAsString.split(",")) {
      result.add(Long.parseLong(id));
    }
    return result;
  }

  public void setOptionIds(Collection<Long> ids) {
    setAttribute_value_ids(ids.stream().map(Number::toString).collect(Collectors.joining(",")));
  }
}
