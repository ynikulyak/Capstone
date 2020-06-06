package capstone.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Order DTO.
 */
public class Order {
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("Y-MM-dd HH:mm");

  public Long id;
  public double total;
  public double tax;
  public String status;
  public List<OrderLineItem> items;
  public Date created;
  public Date ready;
  public Date pickedUp;
  public Customer customer;

  public double getSubTotal() {
    double subTotal = 0.0d;
    for (OrderLineItem cartItem : items) {
      subTotal += cartItem.getPrice();
    }
    return subTotal;
  }

  public String getCreated() {
    return formatDate(created);
  }

  public String getPickedUp() {
    return formatDate(pickedUp);
  }

  public String getReady() {
    return formatDate(ready);
  }

  private String formatDate(Date date) {
    if (date == null || date.getTime() == 0L) {
      return "N/A";
    }
    return LocalDateTime.ofEpochSecond(date.getTime() / 1000L, 0, ZoneOffset.UTC)
        .format(dateTimeFormatter);
  }
}
