package com.order.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.domain.Category;
import com.order.domain.CategoriesRepository;

@Service
public class CategoriesService {

   private static final Logger log = LoggerFactory.getLogger(CategoriesService.class);

   @Autowired
   private CategoriesRepository categoryRepository;

   public Optional<Category> getById(long id) {
      return categoryRepository.findById(id);

   }

   public List<Category> getAll() {
      return categoryRepository.getAllCategories();
   }
}
