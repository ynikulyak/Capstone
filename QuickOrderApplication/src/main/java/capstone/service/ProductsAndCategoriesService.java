package capstone.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import capstone.domain.Category;
import capstone.domain.Product;


@Service
public class ProductsAndCategoriesService {

   private static final Logger log = LoggerFactory.getLogger(ProductsAndCategoriesService.class);
   private RestTemplate restTemplate;
   private String categoriesUrl;
   private String categoryUrl;
   private String allProductsUrl;
   private String productByProductIdUrl;
   private String productsByCategoryIdUrl;

   public ProductsAndCategoriesService(@Value("${pacs.baseurl}") String baseUrl) {
      this.restTemplate = new RestTemplate();
      this.categoryUrl = baseUrl + "/api/category/v1/";
      this.categoriesUrl = baseUrl + "/api/categories/v1/";
      this.allProductsUrl = baseUrl + "/api/all_products/v1/";
      this.productByProductIdUrl = baseUrl + "/api/product/v1/";
      this.productsByCategoryIdUrl = baseUrl + "/api/products/v1/";
   }

   public List<Category> getCategories() {
      log.info("Fetching JSON from " + categoriesUrl);
      ResponseEntity<Category[]> response = restTemplate.getForEntity(categoriesUrl, Category[].class);
      log.info("Status code from PACS server, categories:" + response.getStatusCodeValue());
      return Arrays.asList(response.getBody());
   }

   public Category getCategory(String id) {
      String url = categoryUrl + id;
      log.info("Fetching JSON from " + url);
      ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);
      log.info("Status code from PACS server, category:" + id + " :" + response.getStatusCodeValue());
      return response.getBody();
   }
   
   public Product getProduct(String id) {
      String url = productByProductIdUrl + id;
      log.info("Fetching JSON from " + url);
      ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
      log.info("Status code from PACS server, product:" + id + " :" + response.getStatusCodeValue());
      return response.getBody();
   }
   
   public List<Product> getProductsByCategoryId(String id) {
      String url = productsByCategoryIdUrl + id;
      log.info("Fetching JSON from " + url);
      ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
      log.info("Status code from PACS server, products:" + id + " :" + response.getStatusCodeValue());
      return Arrays.asList(response.getBody());
   }
   
   public List<Product> getAllProducts() {
      log.info("Fetching JSON from " + allProductsUrl);
      ResponseEntity<Product[]> response = restTemplate.getForEntity(allProductsUrl, Product[].class);
      log.info("Status code from PACS server, products:" + allProductsUrl + " :" + response.getStatusCodeValue());
      return Arrays.asList(response.getBody());
   }
}
