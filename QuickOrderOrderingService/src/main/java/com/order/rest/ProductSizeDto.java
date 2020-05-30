package com.order.rest;

/**
 * Rest entity for category.
 */
public class ProductSizeDto {

  private long id;
  private SizeDto size;
  private ProductDto product;
  private double price;

  public ProductSizeDto() {
  }

  public ProductSizeDto(long id, SizeDto size, ProductDto productId, double price) {
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

  public SizeDto getSize() {
    return size;
  }

  public void setSize(SizeDto size) {
    this.size = size;
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
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
