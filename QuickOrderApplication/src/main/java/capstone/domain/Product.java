package capstone.domain;

//class reflection of json 
public class Product {
  public Long id;

  public Category category;

  public String name;

  public String description;

  public String full_image;

  public String thum;

  public Product() {
    this(null, null, null, null, null, null);
  }

  public Product(Long id, Category category, String name, String description, String thum, String image) {
    super();
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.full_image = image;
    this.thum = thum;
  }
}
