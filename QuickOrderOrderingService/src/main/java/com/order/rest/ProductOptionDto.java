package com.order.rest;

/** Rest entity for option. */
public class ProductOptionDto {

   private long id;
   private OptionDto attribute;
   private String attribute_value_name;
   private double price;
   
   public ProductOptionDto() {}

   public ProductOptionDto(long id, OptionDto attribute, String attribute_value_name, double price) {
      super();
      this.id = id;
      this.attribute = attribute;
      this.attribute_value_name = attribute_value_name;
      this.price = price;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public OptionDto getAttribute() {
      return attribute;
   }

   public void setAttribute(OptionDto attribute) {
      this.attribute = attribute;
   }

   public String getAttribute_value_name() {
      return attribute_value_name;
   }

   public void setAttribute_value_name(String attribute_value_name) {
      this.attribute_value_name = attribute_value_name;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "ProductOptions [id=" + id + ", attribute=" + attribute + ", attribute_value_name=" + attribute_value_name
            + ", price=" + price + "]";
   }
}
