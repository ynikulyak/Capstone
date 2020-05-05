package capstone.domain;

//class reflection of json 
public class Product {

   private Long id;
   private Category category;
   private String name;
   private String description;
   private String full_image;
   
   public Product() {
      this(null, null, null, null, null);
   }
   
   public Product(Long id, Category category, String name, String description, String image) {
      super();
      this.id = id;
      this.category = category;
      this.name = name;
      this.description = description;
      this.full_image = image;
   }
}
