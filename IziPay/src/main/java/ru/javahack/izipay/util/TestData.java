package ru.javahack.izipay.util;

import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class TestData {

    public static List<Product> products() {
        return asList(milk(), juice());
    }

    public static Product milk() {
        return new Product(1, "milk", new BigDecimal("1.5"), category());
    }

    public static Product juice() {
        return new Product(2, "juice", new BigDecimal("2.5"), category());
    }


    public static ProductCategory category() {
        return new ProductCategory(1, "food");
    }
}
