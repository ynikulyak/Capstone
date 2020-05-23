package capstone.domain;

import java.util.List;

/* Cart container. */
public class Cart {
    public List<CartItem> items;

    private volatile double subTotalCalculated = Double.NaN;

    public double getSubTotal() {
        if (subTotalCalculated != Double.NaN) {
            return subTotalCalculated;
        }
        double subTotal = 0.0d;
        for (CartItem cartItem : items) {
            subTotal += cartItem.quantity * cartItem.selectedSize.price;
            for (ProductOption productOption : cartItem.selectedOptions) {
                subTotal += productOption.price;
            }
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
