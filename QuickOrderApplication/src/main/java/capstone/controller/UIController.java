package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
   
   @GetMapping("/index")
   public String getIndexPage() {
      return "index";
   }
   
   @GetMapping("/coffee")
   public String getCoffee() {
      return "coffee";
   }
   
   @GetMapping("/food")
   public String getFood() {
      return "food";
   }
   
   @GetMapping("/slushy_freeze")
   public String getSlushyFreeze() {
      return "slushy_freeze";
   }
   
   @GetMapping("/smoothies")
   public String getSmoothies() {
      return "smoothies";
   }
   
   @GetMapping("/specialteas")
   public String getSpecialteas() {
      return "specialteas";
   }
   
   @GetMapping("/teas")
   public String getTeas() {
      return "teas";
   }
   
   @GetMapping("/cart")
   public String getCart() {
      return "cart";
   }
}
