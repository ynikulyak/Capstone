package capstone.controller;

import capstone.domain.OrderAssignment;
import capstone.domain.OrderAssignmentPage;
import capstone.formatter.PriceFormatter;
import capstone.service.OrderAssignmentService;
import capstone.service.OrderService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
  @GetMapping("/kitchen/index")
  public String getIndex(Model model) {
    addStandardAttributes(model);
    return "kitchen-index";
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

  @GetMapping("/kitchen/orders/unassigned")
  public String getUnassignedOrders(@RequestParam(value = "page", required = false) Integer pg, Model model) {
    addStandardAttributes(model);

    pg = pg != null ? pg - 1 : 0;
    OrderAssignmentPage page = orderAssignmentService.getUnassignedAssignments(pg, 10);
    model.addAttribute("page", page);
    return "kitchen-orders-unassigned";
  }

  // Orders that are assigned to currently logged-in person.
  @PostMapping("/kitchen/orders/unassigned")
  public String postAssign(@RequestParam(value = "orderId", required = true) long orderId) {
    Optional<OrderAssignment> orderAssignment = orderAssignmentService.assign(orderId, currentStaffId);
    if (orderAssignment.isPresent()) {
      return "redirect:/kitchen/orders/assigned?assignmentId=" + orderAssignment.get().id;
    }
    return "redirect:/kitchen/orders/unassigned?error=Unable%20to%20assign&orderId=" + orderId;
  }

  @GetMapping("/kitchen/orders/process/{orderAssignmentId}")
  public String getOrderPage(@PathVariable("orderAssignmentId") long orderAssignmentId, Model model) {
    Optional<OrderAssignment> assignmentOptional = orderAssignmentService.get(orderAssignmentId);
    if (!assignmentOptional.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Assigned order was not found for order assignment id: " + orderAssignmentId);
    }

    addStandardAttributes(model);
    OrderAssignment assignment = assignmentOptional.get();
    boolean canEdit = assignment.staff != null && Long.valueOf(currentStaffId).equals(assignment.staff.id);
    model.addAttribute("assignment", assignment);
    model.addAttribute("canEdit", canEdit);
    return "kitchen-orders-process";
  }

  @PostMapping("/kitchen/orders/perform")
  public String postPerform(@RequestParam(value = "orderAssignmentId", required = true) long orderAssignmentId,
                            @RequestParam(value = "operation", required = true) String operation) {
    Optional<OrderAssignment> orderAssignment = Optional.empty();

    switch (operation) {
      case "start":
        orderAssignment = orderAssignmentService.start(orderAssignmentId);
        break;
      case "complete":
        orderAssignment = orderAssignmentService.complete(orderAssignmentId);
        break;
      case "cancel":
        orderAssignment = orderAssignmentService.cancel(orderAssignmentId);
        break;
    }

    String redirectUrl = "redirect:/kitchen/orders/process/" + orderAssignmentId;
    if (!orderAssignment.isPresent()) {
      redirectUrl += "?error=Order%20assignment%20not%20found";
    }
    return redirectUrl;
  }
}
