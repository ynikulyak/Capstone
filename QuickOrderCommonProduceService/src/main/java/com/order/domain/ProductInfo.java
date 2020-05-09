package com.order.domain;

public class ProductInfo {
   
   long id;
   String name;
   String description;
   String size;
   double price;
   String attributeName;
   double attributePrice;
   
   public ProductInfo(long id, Product product, Size size, ProductSize productSize, Option attribute, ProductOption options) {
      this.id = id;
      this.name = product.getName();
      this.description = product.getDescription();
      this.size = size.getVolume();
      this.price = productSize.getPrice();
      this.attributeName = attribute.getName();
      this.attributePrice = options.getPrice();
   }
   
   public ProductInfo(long id, String name, String description, String size, double price, String attributeName,
         double attributePrice) {
      super();
      this.id = id;
      this.name = name;
      this.description = description;
      this.size = size;
      this.price = price;
      this.attributeName = attributeName;
      this.attributePrice = attributePrice;
   }
   
   
   

}
