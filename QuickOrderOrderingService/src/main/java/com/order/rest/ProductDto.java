package com.order.rest;

/** Rest entity for product. */
public class ProductDto {

   private Long id;
   private CategoryDto category;
   private String name;
   private String description;
   private String thum;
   private String full_image;

   public ProductDto() {
   }

   public ProductDto(Long id, CategoryDto category, String name, String description, String thum, String full_image) {
      super();
      this.id = id;
      this.category = category;
      this.name = name;
      this.description = description;
      this.thum = thum;
      this.full_image = full_image;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public CategoryDto getCategory() {
      return category;
   }

   public void setCategory(CategoryDto category) {
      this.category = category;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getThum() {
      return thum;
   }

   public void setThum(String thum) {
      this.thum = thum;
   }

   public String getFull_image() {
      return full_image;
   }

   public void setFull_image(String full_image) {
      this.full_image = full_image;
   }

   @Override
   public String toString() {
      return "Product [id=" + id + ", category=" + category + ", name=" + name + ", description=" + description
            + ", thum=" + thum + ", full_image=" + full_image + "]";
   }
}
