package capstone.service;

import capstone.domain.Cart;
import capstone.domain.Order;
import capstone.domain.PaymentData;
import org.springframework.stereotype.Service;

import static capstone.preconditions.Preconditions.check;
import static capstone.preconditions.Preconditions.checkNotLonger;
import static capstone.preconditions.Preconditions.checkNotNull;

/**
 * Client service for order.
 */
@Service
public class OrderService {

  private static final double MINIMAL_PAYMENT_ALLOWED = 0.01D;
  private static final double MAX_PAYMENT_ALLOWED = 2000.00D;

  private static final int STRING_MAX_LENGTH = 254;
  private static final int CC_MAX_LENGTH = 19;
  private static final int CC_CCV_MAX_LENGTH = 4;
  private static final int CC_EXPIRATION_MAX_LENGTH = 7;
  private static final int STATE_MAX_LENGTH = 2;
  private static final int COUNTRY_MAX_LENGTH = 3;
  private static final int ZIP_MAX_LENGTH = 10;

  private static void validatePaymentData(PaymentData paymentData) throws OrderException {
    try {
      checkNotNull(paymentData, "Cart");
      check(paymentData.firstName, STRING_MAX_LENGTH, "First name");
      check(paymentData.lastName, STRING_MAX_LENGTH, "Last name");

      check(paymentData.email, STRING_MAX_LENGTH, "Email");
      checkNotLonger(paymentData.username, STRING_MAX_LENGTH, "Username");

      check(paymentData.address1, STRING_MAX_LENGTH, "Address line 1");
      checkNotLonger(paymentData.address2, STRING_MAX_LENGTH, "Address line 2");
      check(paymentData.city, STRING_MAX_LENGTH, "City");
      check(paymentData.state, STATE_MAX_LENGTH, "State");
      check(paymentData.country, COUNTRY_MAX_LENGTH, "Country");
      check(paymentData.zipCode, ZIP_MAX_LENGTH, "State");

      check(paymentData.ccName, STRING_MAX_LENGTH, "Name on card");
      check(paymentData.ccNumber, CC_MAX_LENGTH, "Credit card number");
      check(paymentData.ccCvv, CC_CCV_MAX_LENGTH, "CVV");
      check(paymentData.ccExpiration, CC_EXPIRATION_MAX_LENGTH, "Expiration date");
    } catch (IllegalArgumentException iae) {
      throw new OrderException(iae.getMessage(), iae);
    }
  }

  private static void validateCart(Cart cart) throws OrderException {
    try {
      checkNotNull(cart, "Cart can't be null");
      check(cart.items, "Cart can't be empty");
      check(cart.getTotal() >= MINIMAL_PAYMENT_ALLOWED, "Cart can't cost less than 1 cent.");
      check(cart.getTotal() <= MAX_PAYMENT_ALLOWED, "Cart can't cost more than: "
          + MAX_PAYMENT_ALLOWED);
    } catch (IllegalArgumentException iae) {
      throw new OrderException(iae.getMessage(), iae);
    }
  }

  public Long createOrder(Cart cart, PaymentData paymentData) throws OrderException {
    validateCart(cart);
    validatePaymentData(paymentData);
    // TODO: finish
    return Long.MAX_VALUE;
  }

  public Order getOrder(Long id) {
    return null; // TODO: finish
  }

  public static final class OrderException extends Exception {

    public OrderException(String message) {
      super(message);
    }

    public OrderException(String message, Throwable t) {
      super(message, t);
    }
  }
}
