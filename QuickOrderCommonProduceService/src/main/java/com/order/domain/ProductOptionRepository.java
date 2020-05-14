package com.order.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
   
   @Query("select p from ProductOption p where p.attribute.id = :id")
   List<ProductOption> findByOptionId(@PathVariable("id") long id);
   
   @Query("select op from ProductOption op")
   List<ProductOption> findAll();
   
   @Query("select p from ProductOption p where p.id = id")
   Optional<ProductOption> findByAttributeValueId(long id);
}
