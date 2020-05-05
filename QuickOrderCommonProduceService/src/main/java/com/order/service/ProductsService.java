package com.order.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.domain.Product;
import com.order.domain.ProductsRepository;

@Service
public class ProductsService {

   private static final Logger log = LoggerFactory.getLogger(ProductsService.class);

   @Autowired
   private ProductsRepository productsRepository;

   public Optional<Product> getById(long id) {
      return productsRepository.findById(id);
   }

   public List<Product> getByCategory(long categoryId) {
      return productsRepository.getByCategory(categoryId);
   }

   public List<Product> getAllProducts() {
      return productsRepository.getAll();
   }
}
