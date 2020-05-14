package capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import capstone.domain.Category;
import capstone.domain.Product;
import capstone.domain.ProductInfo;
import capstone.service.ProductsAndCategoriesService;

@RestController
public class UIRestController {

   @Autowired
   private ProductsAndCategoriesService productsAndCategoriesService;

   @GetMapping("/productsAndCategoriesServicе/all_products/")
   public List<Product> getProducts() {
      return productsAndCategoriesService.getAllProducts();
   }

   @GetMapping("/productsAndCategoriesService/product/{id}")
   public Product getproduct(@PathVariable("id") String id) {
      return productsAndCategoriesService.getProduct(id);
   }

   @GetMapping("/productsAndCategoriesService/prosucts/{id}")
   public List<Product> getProductsByCategoryId(@PathVariable("code") String id) {
      return productsAndCategoriesService.getProductsByCategoryId(id);
   }

   @GetMapping("/productsAndCategoriesService/categories/")
   public List<Category> getAllCategories() {
      return productsAndCategoriesService.getCategories();
   }

   @GetMapping("/productsAndCategoriesService/category/{id}")
   public Category getCategoryById(@PathVariable("id") String id) {
      return productsAndCategoriesService.getCategory(id);
   }
   
   @GetMapping("/productsAndCtegoriesService/item/{id}")
   public ProductInfo getProductInfo(@PathVariable ("id") long id) {
      return productsAndCategoriesService.getProductInfo(id);
   }
}
