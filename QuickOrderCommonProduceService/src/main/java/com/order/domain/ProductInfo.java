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

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public List<ProductSize> getProductSizesPrice() {
      return productSizesPrice;
   }

   public void setProductSizesPrice(List<ProductSize> productSizesPrice) {
      this.productSizesPrice = productSizesPrice;
   }

   public List<ProductOption> getOptions() {
      return options;
   }

   public void setOptions(List<ProductOption> options) {
      this.options = options;
   }
}
