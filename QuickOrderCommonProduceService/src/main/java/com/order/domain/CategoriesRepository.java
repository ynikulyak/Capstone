package com.order.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
   
   Optional<Category> findById(long id);
   
   @Query("select a from Category a order by a.id")
   List<Category> getAllCategories();
}
 