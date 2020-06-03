package capstone.service;

import capstone.domain.OrderAssignmentPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderAssignmentService {

  private static final Logger log = LoggerFactory.getLogger(OrderService.class);

  private final RestTemplate restTemplate;
  private final String orderServiceGetUnassignedOrdersUrl;
  private final String orderServiceGetAssignedOrdersUrl;

  public OrderAssignmentService(@Value("${paos.baseurl}") String baseUrl) {
    this.restTemplate = new RestTemplate();
    this.orderServiceGetUnassignedOrdersUrl = baseUrl + "/api/assignments/v1/unassigned/";
    this.orderServiceGetAssignedOrdersUrl = baseUrl + "/api/assignments/v1/active/";
  }

  public OrderAssignmentPage getActiveAssignments(long staffId, int page, int size) {
    page = page >= 0 ? page : 0;
    size = size > 0 ? size : 10;

    String url = orderServiceGetAssignedOrdersUrl + staffId + "?page=" + page + "&size=" + size;

    ResponseEntity<OrderAssignmentPage> response =
        restTemplate.getForEntity(url, OrderAssignmentPage.class);

    return response.getBody();
  }

  public OrderAssignmentPage getUnassignedAssignments(int page, int size) {
    page = page >= 0 ? page : 0;
    size = size > 0 ? size : 10;

    String url = orderServiceGetUnassignedOrdersUrl + "?page=" + page + "&size=" + size;

    ResponseEntity<OrderAssignmentPage> response =
        restTemplate.getForEntity(url, OrderAssignmentPage.class);

    return response.getBody();
  }
}
