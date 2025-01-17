package com.order.domain;

import javax.persistence.*;

@Entity
@Table(name = "Categories")
public class Category {

   @Id
   private Long id;
   private String name;

   public Category() {
   }

   public Category(Long id, String name) {
      super();
      this.id = id;
      this.name = name;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Category [id=" + id + ", name=" + name + "]";
   }
}
