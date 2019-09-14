package ru.javahack.izipay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javahack.izipay.db.DataService;
import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;
import ru.javahack.izipay.pojo.api.CountOfProducts;
import ru.javahack.izipay.service.CashBoxService;
import ru.javahack.izipay.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FORESTER
 */
@RestController
@RequestMapping("/api")
public class CashBoxController {

    @Autowired
    private CashBoxService cashBoxService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataService dataService;

    @GetMapping("/products")
    public List<Product> getProductList() {
        return dataService.getAllProducts(userService.getUserId());
    }

    @GetMapping("/categories")
    public List<ProductCategory> getCategoriesList() {
        return dataService.getAllCategories(userService.getUserId());
    }

    @PostMapping("/products")
    public void submitProducts(@RequestBody ArrayList<CountOfProducts> chosenProducts) {
        cashBoxService.submitOrder(chosenProducts);
    }
}
