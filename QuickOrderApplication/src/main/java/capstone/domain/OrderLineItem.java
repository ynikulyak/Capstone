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

  public String getOptions() {
    if (selectedOptions == null || selectedOptions.size() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (ProductOption po : selectedOptions) {
      sb.append(po.attribute_value_name).append(", ");
    }
    sb.setLength(sb.length() - 2);
    return sb.toString();
  }
}
