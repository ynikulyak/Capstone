package capstone.controller;

import capstone.domain.Category;
import capstone.formatter.PriceFormatter;
import capstone.service.OrderService;
import capstone.service.ProductsAndCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class KitchenUIController {

  private static final Logger log = Logger.getLogger(KitchenUIController.class.getName());

  private ProductsAndCategoriesService productsAndCategoriesService;
  private OrderService orderService;
  private PriceFormatter priceFormatter;
  private Environment env;

  public KitchenUIController(ProductsAndCategoriesService productsAndCategoriesService, OrderService orderService,
                             PriceFormatter priceFormatter, Environment env) {
    this.productsAndCategoriesService = productsAndCategoriesService;
    this.orderService = orderService;
    this.priceFormatter = priceFormatter;
    this.env = env;
  }

  private void addStandardAttributes(Model model) {
    model.addAttribute("web_static_prefix", env.getProperty("web.static.prefix", ""));
    model.addAttribute("current_time_millis", System.currentTimeMillis());
    model.addAttribute("price_formatter", priceFormatter);
  }

  // Orders that are assigned to currently logged-in person.
  @GetMapping("/kitchen/orders/assigned")
  public String getAssignedOrders(Model model) {
    addStandardAttributes(model);

    //takes every row in categories table, create category object, put in list, put
    //list in a model and display by html
    Iterable<Category> categories = productsAndCategoriesService.getCategories();
    model.addAttribute("categories", categories);
    return "kitchen-orders-assigned";
  }

  // Orders that are assigned to currently logged-in person.
  @GetMapping("/kitchen/orders/unassigned")
  public String getUnassignedOrders(Model model) {
    addStandardAttributes(model);

    //takes every row in categories table, create category object, put in list, put
    //list in a model and display by html
    Iterable<Category> categories = productsAndCategoriesService.getCategories();
    model.addAttribute("categories", categories);
    return "kitchen-orders-unassigned";
  }
}
