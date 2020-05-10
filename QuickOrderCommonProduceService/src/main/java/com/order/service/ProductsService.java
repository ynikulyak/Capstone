package com.order.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.domain.Option;
import com.order.domain.OptionRepository;
import com.order.domain.Product;
import com.order.domain.ProductOption;
import com.order.domain.ProductOptionRepository;
import com.order.domain.ProductSize;
import com.order.domain.ProductSizeRepository;
import com.order.domain.ProductsRepository;
import com.order.domain.Size;
import com.order.domain.SizeRepository;

@Service
public class ProductsService {

   private static final Logger log = LoggerFactory.getLogger(ProductsService.class);

   @Autowired
   private ProductsRepository productsRepository;
   
   @Autowired
   private SizeRepository sizeRepository;
   
   @Autowired
   private ProductSizeRepository productSizeRepository;
   
   @Autowired
   private OptionRepository optionRepository;
   
   @Autowired
   private ProductOptionRepository productOptionRepository;
   

   public Optional<Product> getById(long id) {
      return productsRepository.findById(id);
   }

   public List<Product> getByCategory(long categoryId) {
      return productsRepository.getByCategory(categoryId);
   }

   public List<Product> getAllProducts() {
      return productsRepository.getAll();
   }
   
   public Optional<Size> getSizeById(long id){
      return sizeRepository.findById(id);
   }
   
   public List<Size> getAllSizes(){
      return sizeRepository.findAllSizes();
   }
   
   public Optional<ProductSize> getPrice(long productId, long sizeId){
      return productSizeRepository.findPriceBySizeIdAndProductId(productId, sizeId);
   }
   
   public Optional<Option> getOption(long id){
      return optionRepository.findById(id);
   }
   
   public List<Option> getAllOptions(){
      return optionRepository.findAll();
   }
   
   public List<ProductOption> getAllAttributes(){
      return productOptionRepository.findAll();
   }
   
   public List<ProductOption> getAllByAttId(long id){
      return productOptionRepository.findByOptionId(id);
   }
   
   public Optional<ProductOption> getAtt(long id) {
      return productOptionRepository.findByAttributeValueId(id);
   }
}
