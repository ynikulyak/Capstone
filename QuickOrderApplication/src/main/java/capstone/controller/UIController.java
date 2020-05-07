package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import capstone.domain.Category;
import capstone.domain.Product;
import capstone.service.ProductsAndCategoriesService;

@Controller
public class UIController {
   
   @Autowired
   private ProductsAndCategoriesService productsAndCategoriesService;
   
   @GetMapping("/index")
   public String getIndexPage(Model model) {
      //takes every row in categories table, create category object, put in list, put
      //list in a model and display by html
      Iterable<Category> categories = productsAndCategoriesService.getCategories();
      model.addAttribute("categories", categories);
      return "index";
   }
   
   @GetMapping("/products/{id}")
   public String getProductsByCategoryId(@PathVariable("id") String id, Model model) {
      
      //takes every row in product table with passed id, create product object, put in list, put
      //list in a model and display by html
      Iterable<Product> products = productsAndCategoriesService.getProductsByCategoryId(id);
      
      model.addAttribute("products", products);  
      return "products";
   }
}
