package com.order.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.order.rest.CategoryDto;
import com.order.rest.ProductOptionDto;
import com.order.rest.ProductSizeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.rest.ProductDto;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductsAndCategoriesService {
  private static final Logger log = LoggerFactory.getLogger(ProductsAndCategoriesService.class);

  private RestTemplate restTemplate;
  private String categoriesUrl;
  private String categoryUrl;
  private String productByProductIdUrl;
  private String productsByCategoryIdUrl;
  private String productSizedByIdsUrl;
  private String productOptionsByIdsUrl;

  public ProductsAndCategoriesService(@Value("${pacs.baseurl}") String baseUrl) {
    this.restTemplate = new RestTemplate();
    this.categoryUrl = baseUrl + "/api/category/v1/";
    this.categoriesUrl = baseUrl + "/api/categories/v1/";
    this.productByProductIdUrl = baseUrl + "/api/product/v1/";
    this.productsByCategoryIdUrl = baseUrl + "/api/products/v1/";
    this.productSizedByIdsUrl = baseUrl + "/api/productSizes/v1/";
    this.productOptionsByIdsUrl = baseUrl + "/api/attributes/v1/";
  }

  public List<CategoryDto> getCategories() {
    log.info("Fetching JSON from " + categoriesUrl);
    ResponseEntity<CategoryDto[]> response = restTemplate.getForEntity(categoriesUrl, CategoryDto[].class);
    log.info("Status code from PACS server, categories:" + response.getStatusCodeValue());
    return Arrays.asList(response.getBody());
  }

  public CategoryDto getCategory(String id) {
    String url = categoryUrl + id;
    log.info("Fetching JSON from " + url);
    ResponseEntity<CategoryDto> response = restTemplate.getForEntity(url, CategoryDto.class);
    log.info("Status code from PACS server, category:" + id + " :" + response.getStatusCodeValue());
    return response.getBody();
  }

  public ProductDto getProduct(String id) {
    String url = productByProductIdUrl + id;
    log.info("Fetching JSON from " + url);
    ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);
    log.info("Status code from PACS server, product:" + id + " :" + response.getStatusCodeValue());
    return response.getBody();
  }

  public List<ProductDto> getProductsByCategoryId(String id) {
    String url = productsByCategoryIdUrl + id;
    log.info("Fetching JSON from " + url);
    ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(url, ProductDto[].class);
    log.info("Status code from PACS server, products:" + id + " :" + response.getStatusCodeValue());
    return Arrays.asList(response.getBody());
  }

  public List<ProductSizeDto> getProductSizes(Collection<Long> productSizeIds) {
    if (productSizeIds.isEmpty()) {
      return Collections.emptyList();
    }
    String url = productSizedByIdsUrl + productSizeIds.stream().map(Number::toString).collect(Collectors.joining(","));
    log.info("Fetching JSON from " + url);
    ResponseEntity<ProductSizeDto[]> response = restTemplate.getForEntity(url, ProductSizeDto[].class);
    log.info("Status code from PACS server, product sizes:" + productSizeIds + " :" + response.getStatusCodeValue());
    return Arrays.asList(response.getBody());
  }

  public List<ProductOptionDto> getProductOptions(Collection<Long> productOptionIds) {
    if (productOptionIds.isEmpty()) {
      return Collections.emptyList();
    }
    String url = productOptionsByIdsUrl +
        productOptionIds.stream().map(Number::toString).collect(Collectors.joining(","));
    log.info("Fetching JSON from " + url);
    ResponseEntity<ProductOptionDto[]> response = restTemplate.getForEntity(url, ProductOptionDto[].class);
    log.info("Status code from PACS server, product options:" +
        productOptionIds + " :" + response.getStatusCodeValue());
    return Arrays.asList(response.getBody());
  }

/*
  public ProductInfoDto getProductInfo(long id) {
    String url = productInfoUrl + id;
    log.info("Fetch JSON from " + url);
    ResponseEntity<ProductInfoDto> response = restTemplate.getForEntity(url, ProductInfoDto.class);
    log.info("Status code from PACS server, productInfo: " + response.getStatusCodeValue());
    return response.getBody();
  }

  public List<ProductInfoDto> getProductInfo(List<Long> ids) {
    if (ids == null || ids.isEmpty()) {
      return Collections.emptyList();
    }
    String commaSeparated = ids.stream()
        .map(Object::toString)
        .collect(Collectors.joining(","));
    String url = productsInfoUrl +
        commaSeparated;
    log.info("Fetch JSON from " + url);
    ResponseEntity<ProductInfoDto[]> response = restTemplate.getForEntity(url, ProductInfoDto[].class);
    log.info("Status code from PACS server, productInfo: " + response.getStatusCodeValue());
    return Arrays.asList(response.getBody());
  }*/
}
