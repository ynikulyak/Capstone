package com.order.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
   
   @Query("select p from Product p where p.size.id = :sizeId and p.productId = productId")
   Optional<ProductSize> findPriceBySizeIdAndProductId(@Param("productId") long productId, @Param("sizeId") long sizeId);

}
