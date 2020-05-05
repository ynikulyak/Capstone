package capstone.domain;

public class Category {

   public Long id;
   public String name;
   
   public Category() {
      this(null, null);
   }
   
   public Category(Long id, String name) {
      super();
      this.id = id;
      this.name = name;
   }
}
