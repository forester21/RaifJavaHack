package ru.javahack.izipay.db;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Заглушка для сервиса запросов в БД
 *
 * @author FORESTER
 */
@Component
@Profile("Test")
public class MockDataService implements DataService{
    @Override
    public ProductCategory addCategory(ProductCategory category) {
        return null;
    }

    @Override
    public Product getProduct(long productId) {
        return chicken();
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(long userId) {
        return asList(chicken(), juice());
    }

    @Override
    public List<ProductCategory> getAllCategories(long userId) {
        return asList(food(), drinks());
    }

    public static Product chicken() {
        return new Product(1, "chicken", new BigDecimal("1.5"), 1);
    }

    public static Product juice() {
        return new Product(2, "juice", new BigDecimal("2.5"), 2);
    }

    public static ProductCategory food() {
        return new ProductCategory(1, "food", 1);
    }

    public static ProductCategory drinks() {
        return new ProductCategory(2, "drinks", 1);
    }
}
