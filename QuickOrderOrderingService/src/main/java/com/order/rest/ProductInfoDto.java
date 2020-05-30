package com.order.rest;

import java.util.List;

public class ProductInfoDto {

  ProductDto product;
  List<ProductSizeDto> productSizes;
  List<ProductOptionDto> options;

  public ProductInfoDto(ProductDto product, List<ProductSizeDto> productSizes, List<ProductOptionDto> options) {
    this.product = product;
    this.productSizes = productSizes;
    this.options = options;
  }

  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public List<ProductSizeDto> getProductSizes() {
    return productSizes;
  }

  public void setProductSizes(List<ProductSizeDto> productSizes) {
    this.productSizes = productSizes;
  }

  public List<ProductOptionDto> getOptions() {
    return options;
  }

  public void setOptions(List<ProductOptionDto> options) {
    this.options = options;
  }
}
