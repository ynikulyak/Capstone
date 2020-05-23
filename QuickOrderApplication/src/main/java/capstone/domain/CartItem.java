package capstone.domain;

import java.util.List;

public class CartItem {
    public ProductInfo productInfo;
    public ProductSize selectedSize;
    public int quantity;
    public List<ProductOption> selectedOptions;
}
