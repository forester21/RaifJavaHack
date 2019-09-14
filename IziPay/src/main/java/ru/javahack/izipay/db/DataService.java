package ru.javahack.izipay.db;

import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;

import java.util.List;

public interface DataService {
    ProductCategory addCategory(ProductCategory category);
    Product addProduct(Product product);
    List<Product> getAllProducts(long userId);
    List<ProductCategory> getAllCategories(long userId);
}
