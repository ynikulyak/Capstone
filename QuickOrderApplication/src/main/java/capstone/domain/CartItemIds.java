package capstone.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Container for cart item identifiers.
 */
public class CartItemIds {

  private static final Logger log = Logger.getLogger(CartItemIds.class.getName());

  public long productId;
  public long productSizeId;
  public int quantity;
  public Set<Long> optionIds;

  public double itemPrice;

  /**
   * Parses encoded cart in the given string.
   * Order URL example: http://localhost:8080/cart?cart=1_1.5.10.8.7!1_2.2.11.13.5!5_9.2.3.2.1!
   * Contains !-separated product, chosen size, quantity and options:
   * {productId}_{productSizeId}.{quantity}.{option1}.{option2}!
   *
   * @param encodedCart Encoded cart.
   * @return List of cart items.
   */
  public static List<CartItemIds> parse(String encodedCart) {
    encodedCart = encodedCart != null ? encodedCart.trim().replaceAll("!$|^!", "") : "";
    if (encodedCart.isEmpty()) {
      return Collections.emptyList();
    }
    String[] encodedItems = encodedCart.split("!");
    if (encodedItems.length == 0) {
      return Collections.emptyList();
    }
    List<CartItemIds> items = new ArrayList<>(encodedItems.length);
    for (String encodedItem : encodedItems) {
      encodedItem = encodedItem.trim().replaceAll("\\.$|^\\.", "");
      String[] parts = encodedItem.split("\\.");
      if (parts.length < 2) {
        log.info("Wrong encoded cart item: " + encodedItem);
        continue;
      }
      String[] productIdParts = parts[0].split("_");
      CartItemIds cartItemIds = new CartItemIds();
      try {
        cartItemIds.productId = Long.parseLong(productIdParts[0]);
        cartItemIds.productSizeId = Long.parseLong(productIdParts[1]);
        cartItemIds.quantity = Integer.parseInt(parts[1]);
        cartItemIds.optionIds = new LinkedHashSet<>();
        for (int i = 2; i < parts.length; i++) {
          String optionIdAsString = parts[i].trim();
          if (optionIdAsString.length() > 0) {
            cartItemIds.optionIds.add(Long.parseLong(optionIdAsString));
          }
        }
        if (cartItemIds.productId > 0 && cartItemIds.productSizeId > 0 &&
            cartItemIds.quantity > 0) {
          items.add(cartItemIds);
        } else {
          log.info(String.format("Invalid data: id %s, size id: %s, quantity: %s, options: %s",
              parts[0], parts[1],
              parts[2], parts[3]));
        }
      } catch (NumberFormatException nfe) {
        log.log(Level.INFO, "Unable to parse some id", nfe);
      }
    }
    return items;
  }
}
