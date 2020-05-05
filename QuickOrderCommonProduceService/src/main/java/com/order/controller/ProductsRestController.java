package com.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.order.domain.Product;
import com.order.service.ProductsService;

@RestController
public class ProductsRestController {

   @Autowired
   private ProductsService productsService;

   @GetMapping("/api/product/v1/{id}")
   public Product getProduct(@PathVariable("id") String id) {

      Optional<Product> product = productsService.getById(Long.parseLong(id));
      // return airportInfo object in form of JSON if object present
      if (product.isPresent()) {
         return product.get();
      }

      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id);
   }

   @GetMapping("/api/products/v1/{categoryId}")
   public List<Product> getProductsFromCategory(@PathVariable("categoryId") String categoryId) {
      return productsService.getByCategory(Long.parseLong(categoryId));
   }
   
   @GetMapping("/api/all_products/v1/")
   public List<Product> getAllProducts() {
      return productsService.getAllProducts();
   }
}
