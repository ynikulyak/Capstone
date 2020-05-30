package com.order.rest;

/** Rest entity for option. */
public class OptionDto {

   private long id;
   private String name;
   
   public OptionDto() {}

   public OptionDto(long id, String name) {
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
