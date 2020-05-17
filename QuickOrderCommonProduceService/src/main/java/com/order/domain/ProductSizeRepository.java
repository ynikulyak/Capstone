package com.order.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
   
   @Query("select ps from ProductSize ps where ps.size.id = :sizeId and ps.product.id = :productId")
   Optional<ProductSize> findPriceBySizeIdAndProductId(@Param("productId") long productId, @Param("sizeId") long sizeId);
   
   @Query("select ps from ProductSize ps where ps.product.id = :id order by ps.size.volume")
   List<ProductSize> findAllById(@Param("id") long id);

}
