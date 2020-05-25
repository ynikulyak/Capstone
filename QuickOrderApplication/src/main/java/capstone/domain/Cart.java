package capstone.domain;

import java.util.List;

/* Cart container. */
public class Cart {
    public List<CartItem> items;


    public double getSubTotal() {
        double subTotal = 0.0d;
        for (CartItem cartItem : items) {
            subTotal += cartItem.getPrice();
        }
        return subTotal;
    }

    public double getTaxPercent() {
        return 0.08d;
    }

    public double getTax() {
        return getTaxPercent() * getSubTotal();
    }

    public double getTotal() {
        double total = getSubTotal();
        return (1.0 + getTaxPercent()) * total;
    }

    public int getCount() {
        int quantity= 0;
        for (CartItem cartItem : items) {
            quantity += cartItem.quantity;
        }
        return quantity;
    }
}
