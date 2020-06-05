package capstone.service;

import capstone.domain.OrderAssignment;
import capstone.domain.OrderAssignmentPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderAssignmentService {

  private static final Logger log = LoggerFactory.getLogger(OrderService.class);

  private final RestTemplate restTemplate;
  private final String getUnassignedOrdersUrl;
  private final String getAssignedOrdersUrl;
  private final String assignUrl;

  private final String assignmentUrl;

  private final String startUrl;
  private final String completeUrl;
  private final String cancelUrl;
  private final String pickupUrl;

  public OrderAssignmentService(@Value("${paos.baseurl}") String baseUrl) {
    this.restTemplate = new RestTemplate();
    this.getUnassignedOrdersUrl = baseUrl + "/api/assignments/v1/unassigned/";
    this.getAssignedOrdersUrl = baseUrl + "/api/assignments/v1/active/";
    this.assignUrl = baseUrl + "/api/assignments/v1/assign/";

    this.assignmentUrl = baseUrl + "/api/assignments/v1/get/";

    this.startUrl = baseUrl + "/api/assignments/v1/start/";
    this.completeUrl = baseUrl + "/api/assignments/v1/complete/";
    this.cancelUrl = baseUrl + "/api/assignments/v1/cancel/";
    this.pickupUrl = baseUrl + "/api/assignments/v1/pickup/";
  }

  public Optional<OrderAssignment> get(long orderAssignmentId) {
    String url = assignmentUrl + orderAssignmentId;
    ResponseEntity<OrderAssignment> response = restTemplate.getForEntity(url, OrderAssignment.class);
    if (response.getStatusCodeValue() == 200) {
      return Optional.of(response.getBody());
    }
    log.info("Unable to find assignment: " + url + " code: " + response.getStatusCodeValue());
    return Optional.empty();
  }

  public OrderAssignmentPage getActiveAssignments(long staffId, int page, int size) {
    page = page >= 0 ? page : 0;
    size = size > 0 ? size : 10;

    String url = getAssignedOrdersUrl + staffId + "?page=" + page + "&size=" + size;

    ResponseEntity<OrderAssignmentPage> response =
        restTemplate.getForEntity(url, OrderAssignmentPage.class);

    return response.getBody();
  }

  public OrderAssignmentPage getUnassignedAssignments(int page, int size) {
    page = page >= 0 ? page : 0;
    size = size > 0 ? size : 10;

    String url = getUnassignedOrdersUrl + "?page=" + page + "&size=" + size;

    ResponseEntity<OrderAssignmentPage> response =
        restTemplate.getForEntity(url, OrderAssignmentPage.class);

    return response.getBody();
  }

  public Optional<OrderAssignment> assign(long orderId, long currentStaffId) {
    // {staffId}/{orderId}
    String url = assignUrl + currentStaffId + '/' + orderId;
    ResponseEntity<OrderAssignment> response =
        restTemplate.getForEntity(url, OrderAssignment.class);
    if (response.getStatusCodeValue() == 200) {
      return Optional.of(response.getBody());
    }
    log.info("Unable to assign order: " + url + " code: " + response.getStatusCodeValue());
    return Optional.empty();
  }

  public Optional<OrderAssignment> start(long assignmentId) {
    return perform(startUrl + assignmentId);
  }

  public Optional<OrderAssignment> complete(long assignmentId) {
    return perform(completeUrl + assignmentId);
  }

  public Optional<OrderAssignment> cancel(long assignmentId) {
    return perform(cancelUrl + assignmentId);
  }

  public Optional<OrderAssignment> pickup(long assignmentId) {
    return perform(pickupUrl + assignmentId);
  }

  private Optional<OrderAssignment> perform(String url) {
    ResponseEntity<OrderAssignment> response =
        restTemplate.getForEntity(url, OrderAssignment.class);
    if (response.getStatusCodeValue() == 200) {
      return Optional.of(response.getBody());
    }
    log.info("Unable to assign order: " + url + " code: " + response.getStatusCodeValue());
    return Optional.empty();
  }
}
