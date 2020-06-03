package capstone.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CartItem {
  public ProductInfo productInfo;

  public ProductSize selectedSize;

  public List<ProductOption> selectedOptions;

  public int quantity;

  public double getPrice() {
    double price = quantity * selectedSize.price;
    for (ProductOption productOption : selectedOptions) {
      price += productOption.price;
    }
    return price;
  }

  public CartItemIds toItemIds() {
    CartItemIds ids = new CartItemIds();
    ids.itemPrice = getPrice();
    ids.productId = productInfo.product.id;
    ids.productSizeId = selectedSize.id;
    ids.quantity = quantity;
    ids.optionIds = selectedOptions.stream().map(o -> o.id).collect(Collectors.toSet());
    return ids;
  }
}
