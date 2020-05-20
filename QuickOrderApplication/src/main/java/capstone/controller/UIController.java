package capstone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.logging.Logger;
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

@Controller
public class UIController {

    private static final Logger log = Logger.getLogger(UIController.class);

    @Autowired
    private Environment env;

    @Autowired
    private ProductsAndCategoriesService productsAndCategoriesService;

    private void addStandardAttributes(Model model) {
        model.addAttribute("web_static_prefix", env.getProperty("web.static.prefix", ""));
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
    public String getCart(Model model) {
       return "cart";
    }
}
