package com.order.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

   Optional<Product> findById(long id);

   @Query("select p from Product p order by p.name")
   List<Product> getAll();

   @Query("select p from Product p where p.category.id = :categoryId order by p.name")
   List<Product> getByCategory(@Param("categoryId") long categoryId);
   
   
}
