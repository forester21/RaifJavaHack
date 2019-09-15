package ru.javahack.izipay.db;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author FORESTER
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoData {

    @Autowired
    DataService dataService;

    /**
     * Наполнение тестовыми данными
     */
    @Test
    @Ignore
    public void fillData() {
        ProductCategory cat1 = dataService.addCategory(new ProductCategory("Еда", 1));
        ProductCategory cat2 = dataService.addCategory(new ProductCategory("Напитки", 1));
        dataService.addProduct(new Product("Борщ", new BigDecimal(55), cat1.getId()));
        dataService.addProduct(new Product("Макароны", new BigDecimal(25), cat2.getId()));
        dataService.addProduct(new Product("Компот", new BigDecimal(20), cat1.getId()));
        dataService.addProduct(new Product("Сок", new BigDecimal(30), cat2.getId()));
    }

    @Test
    public void get() {
        assertNotNull(dataService.getAllProducts(1).get(0).getName());
    }

    @Test
    public void getProduct() {
        Product product = dataService.getProduct(5);
        assertNotNull(product);
        System.out.println(product.toString());
    }

    @Test
    public void getCategories() {
        List<ProductCategory> categories = dataService.getAllCategories(1);
        assertNotNull(categories.get(0));
        for (ProductCategory pc: categories) {
            System.out.println(pc.toString());
        }
    }

    @Test
    public void getProducts() {
        List<Product> products = dataService.getAllProducts(2);
        assertNotNull(products.get(0));
        for (Product p: products) {
            System.out.println(p.toString());
        }
    }
}
