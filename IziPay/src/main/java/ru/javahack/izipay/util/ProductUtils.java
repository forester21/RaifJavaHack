package ru.javahack.izipay.util;

import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;
import ru.javahack.izipay.pojo.api.ProductsByCategory;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author FORESTER
 */
public class ProductUtils {

    public static List<ProductsByCategory> groupProductsByCategories(List<Product> products){
        Map<ProductCategory, List<Product>> productsByCategories = new HashMap<>();
        for (Product product : products) {
            productsByCategories.computeIfAbsent(product.getProductCategory(), k -> new ArrayList<>()).add(product);
        }
        return productsByCategories.entrySet().stream().map(entry -> new ProductsByCategory(entry.getKey(), entry.getValue())).collect(toList());
    }

}
