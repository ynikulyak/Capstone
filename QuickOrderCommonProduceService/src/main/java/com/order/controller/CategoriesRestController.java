package com.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.order.domain.Category;
import com.order.service.CategoriesService;

@RestController
public class CategoriesRestController {

   @Autowired
   private CategoriesService categoriesService;

   @GetMapping("/api/category/v1/{id}")
   public Category getCategory(@PathVariable("id") long id) {

      Optional<Category> category = categoriesService.getById(id);
      if (category.isPresent()) {
         return category.get();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
   }
   
   @GetMapping("/api/categories/v1/")
   public List<Category> getAllAirports() {

      List<Category> airports = categoriesService.getAll();
      
      return airports;
   }
}
