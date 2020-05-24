package capstone.domain;

import java.util.List;

public class CartItem {
    public ProductInfo productInfo;
    public ProductSize selectedSize;
    public List<ProductOption> selectedOptions;
    public int quantity;

    public double getPrice() {
        double price = quantity * selectedSize.price;
        for (ProductOption productOption : selectedOptions) {
            price += productOption.price;
        }
        return price;
    }
}
