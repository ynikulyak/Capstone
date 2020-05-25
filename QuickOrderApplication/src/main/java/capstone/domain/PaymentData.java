package capstone.domain;

/**
 * Payment data.
 */
public class PaymentData {
    public String firstName;
    public String lastName;

    // Billing address
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zipCode;

    // Card data
    public String ccNumber;
    public String expiration;
    public String cvv;
}
