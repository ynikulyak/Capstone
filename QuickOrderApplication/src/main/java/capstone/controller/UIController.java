package capstone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import capstone.domain.Cart;
import capstone.domain.CartItemIds;
import capstone.domain.PaymentData;
import capstone.util.PriceFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import capstone.domain.Category;
import capstone.domain.Product;
import capstone.domain.ProductInfo;
import capstone.domain.ProductOption;
import capstone.service.ProductsAndCategoriesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UIController {

    private static final Logger log = Logger.getLogger(UIController.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    private ProductsAndCategoriesService productsAndCategoriesService;

    @Autowired
    private PriceFormatter priceFormatter;

    private void addStandardAttributes(Model model) {
        model.addAttribute("web_static_prefix", env.getProperty("web.static.prefix", ""));
        model.addAttribute("current_time_millis", System.currentTimeMillis());
        model.addAttribute("price_formatter", priceFormatter);
    }

    @GetMapping("/index")
    public String getIndexPage(Model model) {
        addStandardAttributes(model);

        //takes every row in categories table, create category object, put in list, put
        //list in a model and display by html
        Iterable<Category> categories = productsAndCategoriesService.getCategories();
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/products/{id}")
    public String getProductsByCategoryId(@PathVariable("id") String id, Model model) {
        addStandardAttributes(model);

        //takes every row in product table with passed id, create product object, put in list, put
        //list in a model and display by html
        Iterable<Product> products = productsAndCategoriesService.getProductsByCategoryId(id);

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/item/{id}")
    public String getItemByProductId(@PathVariable("id") Long id, Model model) {
        addStandardAttributes(model);

        ProductInfo prInfo = productsAndCategoriesService.getProductInfo(id);
        log.info(String.format("Product sizes: %s", prInfo.productSizesPrice));
        model.addAttribute("productInfo", prInfo);

        Map<String, List<ProductOption>> optionsMappedByAttributeName = new HashMap<>();
        for (ProductOption productOption : prInfo.options) {
            if (optionsMappedByAttributeName.get(productOption.attribute.name) == null) {
                optionsMappedByAttributeName.put(productOption.attribute.name, new ArrayList<>());
            }
            optionsMappedByAttributeName.get(productOption.attribute.name).add(productOption);
        }
        model.addAttribute("optionsMappedByAttributeName", optionsMappedByAttributeName);

        Set<String> attributeNames = new LinkedHashSet<>();
        for (ProductOption option : prInfo.options) {
            attributeNames.add(option.attribute.name);
        }
        model.addAttribute("attributeNames", attributeNames);

        return "item";
    }

    @GetMapping("/cart")
    public String getCart(@RequestParam("cart") String encodedCart, Model model) {
        addStandardAttributes(model);
        Optional<Cart> cart =
                productsAndCategoriesService.getCart(CartItemIds.parse(encodedCart));
        model.addAttribute("cartIsEmpty", !cart.isPresent());
        if (cart.isPresent()) {
            model.addAttribute("cart", cart.get());
        }
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(@RequestParam("cart") String encodedCart, Model model) {
        Optional<Cart> cart =
                productsAndCategoriesService.getCart(CartItemIds.parse(encodedCart));
        if (!cart.isPresent()) {
            return "redirect:/cart?cart=";
        }

        addStandardAttributes(model);
        model.addAttribute("cart", cart.get());

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String postCheckout(@RequestParam("cart") String encodedCart, Model model) {
        Optional<Cart> optionalCart =
                productsAndCategoriesService.getCart(CartItemIds.parse(encodedCart));
        if (!optionalCart.isPresent()) {
            return "redirect:/cart?cart=";
        }

        addStandardAttributes(model);
        Cart cart = optionalCart.get();


        String orderId = productsAndCategoriesService.createOrder(cart, new PaymentData());

        return "redirect:/order?order=" + orderId;
    }
}
