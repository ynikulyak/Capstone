package capstone.domain;

import java.util.List;

/* Cart container. */
public class Cart {
    public List<CartItem> items;

    private volatile double subTotalCalculated = Double.NEGATIVE_INFINITY;

    public double getSubTotal() {
        if (subTotalCalculated != Double.NEGATIVE_INFINITY) {
            return subTotalCalculated;
        }
        double subTotal = 0.0d;
        for (CartItem cartItem : items) {
            subTotal += cartItem.getPrice();
        }
        subTotalCalculated = subTotal;
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
}
