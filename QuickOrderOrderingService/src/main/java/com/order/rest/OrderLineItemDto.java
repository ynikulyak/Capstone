package com.order.rest;

import com.order.domain.LineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class OrderLineItemDto {

  public Long id;
  public ProductSizeDto selectedSize;
  public List<ProductOptionDto> selectedOptions;
  public int quantity;
  public double totalPrice;

  public static List<OrderLineItemDto> from(
      List<LineItem> lineItems, Function<Long, ProductSizeDto> sizeProvider,
      Function<Long, ProductOptionDto> optionProvider) {
    List<OrderLineItemDto> result = new ArrayList<>(lineItems.size());
    for (LineItem lineItem : lineItems) {
      Set<Long> optionIds = lineItem.getOptionIds();
      OrderLineItemDto dto = new OrderLineItemDto();
      dto.id = lineItem.getId();
      dto.quantity = lineItem.getQuantity();
      dto.totalPrice = lineItem.getTotalPrice();
      dto.selectedSize = sizeProvider.apply(lineItem.getProductSizeId());
      dto.selectedOptions = new ArrayList<>(optionIds.size());
      for (Long optionId : optionIds) {
        dto.selectedOptions.add(optionProvider.apply(optionId));
      }
      result.add(dto);
    }
    return result;
  }
}
