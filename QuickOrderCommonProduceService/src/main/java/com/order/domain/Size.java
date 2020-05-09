package com.order.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Sizes")
public class Size {
   
   @Id
   private long id;
   private String volume;
   
   public Size() {}
   
   public Size(long id, String volume) {
      super();
      this.id = id;
      this.volume = volume;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getVolume() {
      return volume;
   }

   public void setVolume(String volume) {
      this.volume = volume;
   }

   @Override
   public String toString() {
      return "Sizes [id=" + id + ", volume=" + volume + "]";
   }
}
