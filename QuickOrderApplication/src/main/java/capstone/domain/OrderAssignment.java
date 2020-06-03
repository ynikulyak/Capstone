package capstone.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Order assignment DOT.
 */
public class OrderAssignment {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("Y-M-d H:m");

  public Long id;

  public Staff staff;

  public Order order;

  public String status;

  public Date created;

  public String getCreateFormatted() {
    return LocalDateTime.ofEpochSecond(created.getTime() / 1000L, 0, ZoneOffset.UTC)
        .format(dateTimeFormatter);
  }
}

