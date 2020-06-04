package capstone.controller;

import capstone.domain.Category;
import capstone.domain.OrderAssignment;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class KitchenUIController {

  private static final Logger log = Logger.getLogger(KitchenUIController.class.getName());

  private static final long currentStaffId = 10005L; // TODO: set staff id from logged in cookie/session.

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
  public String getAssignedOrders(@RequestParam(value = "page", required = false) Integer pg, Model model) {
    addStandardAttributes(model);

    pg = pg != null ? pg - 1 : 0;
    OrderAssignmentPage page = orderAssignmentService.getActiveAssignments(currentStaffId, pg, 10);
    model.addAttribute("page", page);
    return "kitchen-orders-assigned";
  }

  // Orders that are assigned to currently logged-in person.
  @GetMapping("/kitchen/orders/unassigned")
  public String getUnassignedOrders(@RequestParam(value = "page", required = false) Integer pg, Model model) {
    addStandardAttributes(model);

    pg = pg != null ? pg - 1 : 0;
    OrderAssignmentPage page = orderAssignmentService.getUnassignedAssignments(pg, 10);
    model.addAttribute("page", page);
    return "kitchen-orders-unassigned";
  }

  @PostMapping("/kitchen/orders/unassigned")
  public String postAssign(@RequestParam(value = "orderId", required = true) long orderId) {
    Optional<OrderAssignment> orderAssignment = orderAssignmentService.assign(orderId, currentStaffId);
    if (orderAssignment.isPresent()) {
      return "redirect:/kitchen/orders/assigned?assignmentId=" + orderAssignment.get().id;
      //return "redirect:/kitchen/orders/process?assignmentId=" + orderAssignment.get().id;
    }
    return "redirect:/kitchen/orders/unassigned?error=Unable%20to%20assign&orderId=" + orderId;
  }
}
