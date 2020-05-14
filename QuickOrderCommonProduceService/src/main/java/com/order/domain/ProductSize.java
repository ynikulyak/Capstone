package com.order.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product_sizes")
public class ProductSize {
   
   @Id
   private long id;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "size_id")
   private Size size;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "product_id")
   private Product product;
   private double price;
   
   public ProductSize() {}

   public ProductSize(long id, Size size, Product productId, double price) {
      super();
      this.id = id;
      this.size = size;
      this.product = productId;
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

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "ProductSize [id=" + id + ", size=" + size + ", productId=" + product + ", price=" + price + "]";
   }
}
