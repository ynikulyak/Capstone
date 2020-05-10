package com.order.domain;

import java.util.List;

public class ProductInfo {
   
   Product product;
   List<ProductSize> productSizesPrice;
   List<ProductOption> options;
   

   
   public ProductInfo(Product product, List<ProductSize> productSizePrice, List<ProductOption> options) {
      this.product = product;
      this.productSizesPrice = productSizePrice;
      this.options = options;
   }
}
