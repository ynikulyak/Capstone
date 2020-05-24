package capstone.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public final class PriceFormatter {

    public String getPrice(double price) {
        return String.format("%.2f", price);
    }

    public String getUsd(double price) {
        return String.format("$%.2f", price);
    }
}
