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
   @Column(name="product_id")
   private int productId;
   private double price;
   
   public ProductSize() {}

   public ProductSize(long id, Size size, int product_id, double price) {
      super();
      this.id = id;
      this.size = size;
      this.productId = product_id;
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

   public int getProduct_id() {
      return productId;
   }

   public void setProduct_id(int product_id) {
      this.productId = product_id;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "ProductSize [id=" + id + ", size=" + size + ", product_id=" + productId + ", price=" + price + "]";
   }
}
