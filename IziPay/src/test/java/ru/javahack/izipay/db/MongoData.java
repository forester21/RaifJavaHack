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
        dataService.addCategory(new ProductCategory("Еда", 1));
        dataService.addCategory(new ProductCategory("Напитки", 1));
        dataService.addProduct(new Product("Лагман", new BigDecimal(55), 1));
        dataService.addProduct(new Product("Гречка", new BigDecimal(25), 1));
        dataService.addProduct(new Product("Компот", new BigDecimal(20), 2));
        dataService.addProduct(new Product("Сок", new BigDecimal(30), 2));
    }

    @Test
    public void get() {
        assertNotNull(dataService.getAllProducts(1).get(0).getName());
    }
}
