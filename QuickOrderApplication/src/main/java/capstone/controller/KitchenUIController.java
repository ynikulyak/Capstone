package capstone.controller;

import capstone.domain.Category;
import capstone.domain.OrderAssignmentPage;
import capstone.formatter.PriceFormatter;
import capstone.service.OrderAssignmentService;
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

  private OrderAssignmentService orderAssignmentService;
  private OrderService orderService;
  private PriceFormatter priceFormatter;
  private Environment env;

  public KitchenUIController(OrderAssignmentService orderAssignmentService, OrderService orderService,
                             PriceFormatter priceFormatter, Environment env) {
    this.orderAssignmentService = orderAssignmentService;
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

    long staffId = 123456L;

    OrderAssignmentPage page = orderAssignmentService.getActiveAssignments(staffId, 0, 10);
    model.addAttribute("page", page);
    return "kitchen-orders-assigned";
  }

  // Orders that are assigned to currently logged-in person.
  @GetMapping("/kitchen/orders/unassigned")
  public String getUnassignedOrders(Model model) {
    addStandardAttributes(model);

    OrderAssignmentPage page = orderAssignmentService.getUnassignedAssignments(0, 10);
    model.addAttribute("page", page);
    return "kitchen-orders-unassigned";
  }
}
