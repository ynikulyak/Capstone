package capstone.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import capstone.domain.Cart;
import capstone.domain.CartItem;
import capstone.domain.CartItemIds;
import capstone.domain.ProductOption;
import capstone.domain.ProductSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import capstone.domain.Category;
import capstone.domain.Product;
import capstone.domain.ProductInfo;


@Service
public class ProductsAndCategoriesService {

    private static final Logger log = LoggerFactory.getLogger(ProductsAndCategoriesService.class);
    private RestTemplate restTemplate;
    private String categoriesUrl;
    private String categoryUrl;
    private String allProductsUrl;
    private String productByProductIdUrl;
    private String productsByCategoryIdUrl;
    private String productInfoUrl;
    private String productsInfoUrl;

    public ProductsAndCategoriesService(@Value("${pacs.baseurl}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.categoryUrl = baseUrl + "/api/category/v1/";
        this.categoriesUrl = baseUrl + "/api/categories/v1/";
        this.allProductsUrl = baseUrl + "/api/all_products/v1/";
        this.productByProductIdUrl = baseUrl + "/api/product/v1/";
        this.productsByCategoryIdUrl = baseUrl + "/api/products/v1/";
        this.productInfoUrl = baseUrl + "/api/productInfo/v1/";
        this.productsInfoUrl = baseUrl + "/api/productInfos/v1/";
    }

    public List<Category> getCategories() {
        log.info("Fetching JSON from " + categoriesUrl);
        ResponseEntity<Category[]> response = restTemplate.getForEntity(categoriesUrl, Category[].class);
        log.info("Status code from PACS server, categories:" + response.getStatusCodeValue());
        return Arrays.asList(response.getBody());
    }

    public Category getCategory(String id) {
        String url = categoryUrl + id;
        log.info("Fetching JSON from " + url);
        ResponseEntity<Category> response = restTemplate.getForEntity(url, Category.class);
        log.info("Status code from PACS server, category:" + id + " :" + response.getStatusCodeValue());
        return response.getBody();
    }

    public Product getProduct(String id) {
        String url = productByProductIdUrl + id;
        log.info("Fetching JSON from " + url);
        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        log.info("Status code from PACS server, product:" + id + " :" + response.getStatusCodeValue());
        return response.getBody();
    }

    public List<Product> getProductsByCategoryId(String id) {
        String url = productsByCategoryIdUrl + id;
        log.info("Fetching JSON from " + url);
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        log.info("Status code from PACS server, products:" + id + " :" + response.getStatusCodeValue());
        return Arrays.asList(response.getBody());
    }

    public List<Product> getAllProducts() {
        log.info("Fetching JSON from " + allProductsUrl);
        ResponseEntity<Product[]> response = restTemplate.getForEntity(allProductsUrl, Product[].class);
        log.info("Status code from PACS server, products:" + allProductsUrl + " :" + response.getStatusCodeValue());
        return Arrays.asList(response.getBody());
    }

    public ProductInfo getProductInfo(long id) {
        String url = productInfoUrl + id;
        log.info("Fetch JSON from " + url);
        ResponseEntity<ProductInfo> response = restTemplate.getForEntity(url, ProductInfo.class);
        log.info("Status code from PACS server, productInfo: " + response.getStatusCodeValue());
        return response.getBody();
    }

    public List<ProductInfo> getProductInfo(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        String commaSeparated = ids.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        String url = productsInfoUrl +
                commaSeparated;
        log.info("Fetch JSON from " + url);
        ResponseEntity<ProductInfo[]> response = restTemplate.getForEntity(url, ProductInfo[].class);
        log.info("Status code from PACS server, productInfo: " + response.getStatusCodeValue());
        return Arrays.asList(response.getBody());
    }

    public Optional<Cart> getCart(List<CartItemIds> cartItemIdsList) {
        List<ProductInfo> productInfos =
                getProductInfo(cartItemIdsList.stream().map(item -> item.productId)
                        .collect(Collectors.toList()));
        if (productInfos.isEmpty()) {
            return Optional.empty();
        }
        Map<Long, ProductOption> optionsMap = new HashMap<>();
        Map<Long, ProductInfo> productInfoMap = new HashMap<>();
        for (ProductInfo productInfo : productInfos) {
            productInfoMap.put(productInfo.product.id, productInfo);
            for (ProductOption option  : productInfo.options) {
                optionsMap.putIfAbsent(option.id, option);
            }
        }
        Cart result = new Cart();
        result.items = new ArrayList<>(cartItemIdsList.size());
        for (CartItemIds cartItemIds : cartItemIdsList) {
            if (!productInfoMap.containsKey(cartItemIds.productId)) {
                log.info("Product not found by id: " + cartItemIds.productId);
                continue;
            }
            ProductInfo productInfo = productInfoMap.get(cartItemIds.productId);
            ProductSize productSize = null;
            for (ProductSize ps : productInfo.productSizesPrice) {
                if (ps.id == cartItemIds.productSizeId) {
                    productSize = ps;
                    break;
                }
            }
            if (productSize == null) {
                log.info("Product size not found by id: " + cartItemIds.productSizeId);
                continue;
            }
            CartItem cartItem = new CartItem();
            cartItem.productInfo = productInfoMap.get(cartItemIds.productId);
            cartItem.selectedSize = productSize;
            cartItem.quantity = cartItemIds.quantity;
            cartItem.selectedOptions = new ArrayList<>();
            for (Long productOptionId : cartItemIds.optionIds) {
                ProductOption productOption = optionsMap.get(productOptionId);
                if (productOption != null) {
                    cartItem.selectedOptions.add(productOption);
                }
            }
            result.items.add(cartItem);
        }
        return Optional.of(result);
    }
}
