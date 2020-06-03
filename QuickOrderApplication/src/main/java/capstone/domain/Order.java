package capstone.domain;

import java.util.Date;
import java.util.List;

/**
 * Order DTO.
 */
public class Order {
  public Long id;

  public double total;

  public double tax;

  public String status;

  public List<OrderLineItem> items;

  public Date created;

  public Date ready;

  public Date pickedUp;

  public Customer customer;
}
