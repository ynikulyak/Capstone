package com.order.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Attribute_values")
public class ProductOption {
   
   @Id
   private long id;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "attribute_id")
   private Option attribute;
   private String attribute_value_name;
   private double price;
   
   public ProductOption() {}

   public ProductOption(long id, Option attribute, String attribute_value_name, double price) {
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

   public Option getAttribute() {
      return attribute;
   }

   public void setAttribute(Option attribute) {
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
