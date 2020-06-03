package capstone.domain;

import java.util.List;

/**
 * Order line items.
 */
public class OrderLineItem {
  public Long id;

  public ProductSize selectedSize;

  public int quantity;

  public double totalPrice;

  public List<ProductOption> selectedOptions;
}
