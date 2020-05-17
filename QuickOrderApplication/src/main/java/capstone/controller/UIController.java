package capstone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import capstone.domain.Category;
import capstone.domain.Product;
import capstone.domain.ProductInfo;
import capstone.domain.ProductOption;
import capstone.service.ProductsAndCategoriesService;

@Controller
public class UIController {

   @Autowired
   private Environment env;

   @Autowired
   private ProductsAndCategoriesService productsAndCategoriesService;

   private void addStandardAttributes(Model model) {
      model.addAttribute("web_static_prefix", env.getProperty("web.static.prefix", ""));
   }
   
   @GetMapping("/index")
   public String getIndexPage(Model model) {
      addStandardAttributes(model);

      //takes every row in categories table, create category object, put in list, put
      //list in a model and display by html
      Iterable<Category> categories = productsAndCategoriesService.getCategories();
      model.addAttribute("categories", categories);
      return "index";
   }
   
   @GetMapping("/products/{id}")
   public String getProductsByCategoryId(@PathVariable("id") String id, Model model) {
      addStandardAttributes(model);
      
      //takes every row in product table with passed id, create product object, put in list, put
      //list in a model and display by html
      Iterable<Product> products = productsAndCategoriesService.getProductsByCategoryId(id);
      
      model.addAttribute("products", products);  
      return "products";
   }
   
   @GetMapping("/item/{id}")
   public String getItemByProductId(@PathVariable ("id") Long id, Model model) {
      addStandardAttributes(model);

      ProductInfo prInfo = productsAndCategoriesService.getProductInfo(id);
      model.addAttribute("productInfo", prInfo);
      
      Map<String, ArrayList<String>> names = new HashMap<>();
      
      for(ProductOption p : prInfo.options) {
         if(names.get(p.attribute.name) == null) {
            names.put(p.attribute.name, new ArrayList<String>());
         }
         names.get(p.attribute.name).add(p.attribute_value_name);
      }
      
      model.addAttribute("map", names);
      
      Set<String> names2 = new LinkedHashSet<String>();
      
      for(ProductOption p : prInfo.options) {
         names2.add(p.attribute.name);
      }
      
      model.addAttribute("addOns", names2);
      return "item";
   }
}
