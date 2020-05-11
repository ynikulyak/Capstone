package com.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Sizes")
public class ProductSize {
   
   @Id
   private long id;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "size_id")
   private Size size;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "product_id")
   private Product productId;
   private double price;
   
   public ProductSize() {}

   public ProductSize(long id, Size size, Product productId, double price) {
      super();
      this.id = id;
      this.size = size;
      this.productId = productId;
      this.price = price;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Size getSize() {
      return size;
   }

   public void setSize(Size size) {
      this.size = size;
   }

   public Product getProductId() {
      return productId;
   }

   public void setProductId(Product productId) {
      this.productId = productId;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "ProductSize [id=" + id + ", size=" + size + ", productId=" + productId + ", price=" + price + "]";
   }
}
