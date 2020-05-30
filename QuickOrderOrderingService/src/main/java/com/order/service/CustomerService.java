package com.order.service;

import com.order.domain.Customer;
import com.order.domain.CustomerRepository;
import com.order.rest.PaymentDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Customer service.
 */
@Service
public class CustomerService {

  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

  private final CustomerRepository customerRepository;
  private HashingService hashingService;

  public CustomerService(CustomerRepository customerRepository, HashingService hashingService) {
    this.customerRepository = customerRepository;
    this.hashingService = hashingService;
  }

  /**
   * Creates customer or returns existing one with the same password. Doesn't commit (flush).
   */
  public Customer createCustomer(PaymentDataDto paymentDataDto) {
    String passwordHash = hashingService.hash(paymentDataDto.getPassword());
    List<Customer> existingCustomer =
        customerRepository.findByEmailAndPassword(paymentDataDto.getEmail(), passwordHash);
    if (!existingCustomer.isEmpty()) {
      return existingCustomer.stream().findFirst().get();
    }

    Customer customer = paymentDataDto.toCustomer();
    customer.setPasswordHash(passwordHash);

    log.info("Saving new customer data... ");

    return customerRepository.save(customer);
  }
}
