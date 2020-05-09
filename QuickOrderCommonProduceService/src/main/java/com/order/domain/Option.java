package com.order.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Attributes")
public class Option {
   
   @Id
   private long id;
   private String name;
   
   public Option() {}

   public Option(long id, String name) {
      super();
      this.id = id;
      this.name = name;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
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
      return "Options [id=" + id + ", name=" + name + "]";
   }
}
