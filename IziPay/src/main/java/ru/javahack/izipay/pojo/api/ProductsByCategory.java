package ru.javahack.izipay.pojo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;

import java.util.List;

/**
 * @author FORESTER
 */
@Getter
@AllArgsConstructor
public class ProductsByCategory {

    private ProductCategory productCategory;
    private List<Product> products;
}
