package com.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.order.domain.Option;
import com.order.domain.Product;
import com.order.domain.ProductOption;
import com.order.domain.ProductSize;
import com.order.domain.Size;
import com.order.service.ProductsService;

@RestController
public class ProductsRestController {

   @Autowired
   private ProductsService productsService;

   @GetMapping("/api/product/v1/{id}")
   public Product getProduct(@PathVariable("id") String id) {

      Optional<Product> product = productsService.getById(Long.parseLong(id));
      // return product object in form of JSON if object present
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
   
   @GetMapping("/api/size/v1/{id}")
   public Size getSizeById(@PathVariable("id") long id) {
      Optional<Size> size =  productsService.getSizeById(id);
      //return size object in form of JSON if object is present
      if(size.isPresent()) {
         return size.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Size not found: " + id);
   }
   
   @GetMapping("/api/product_size/v1/{productId}/{sizeId}")
   public ProductSize getPriceByIds(@PathVariable("productId") long productId, @PathVariable("sizeId") long sizeId) {
      Optional<ProductSize> productSize = productsService.getPrice(productId, sizeId);
      if(productSize.isPresent()) {
         return productSize.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price was not found: " + productId);
      
   }
   
   @GetMapping("/api/option/v1/{id}")
   public Option getOption(@PathVariable ("id") long id) {
      Optional<Option> op = productsService.getOption(id);
      
      if(op.isPresent()) {
         return op.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attribute was not found: " + id);
   }
   
   @GetMapping("/api/all_options/v1/")
   public List<Option> getAllOptions(){
      return productsService.getAllOptions();
   }
   
   @GetMapping("/api/all_attributes/v1/")
   public List<ProductOption> getAllAttributes(){
      return productsService.getAllAttributes();
   }
   
   @GetMapping("/api/attribute_values/v1/{id}")
   public List<ProductOption> getAllValuesForAttribute(@PathVariable ("id") long id){
      return productsService.getAllByAttId(id);
   }
   
   @GetMapping("/api/attribute/v1/{id}")
   public ProductOption getAttribute(@PathVariable("id") long id) {
      Optional<ProductOption> attribute = productsService.getAtt(id);
      
      if(attribute.isPresent()) {
         return attribute.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attribute was not found: " + id);
   }
}
