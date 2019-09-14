package ru.javahack.izipay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.api.CountOfProducts;
import ru.javahack.izipay.service.CashBoxService;
import ru.javahack.izipay.util.TestData;

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

    @GetMapping("/products")
    public List<Product> getProductList() {
        return TestData.products();
    }

    @PostMapping("/products")
    public void submitProducts(@RequestBody ArrayList<CountOfProducts> chosenProducts) {
        cashBoxService.submitOrder(chosenProducts);
    }

//    @GetMapping("/testSocket")
//    public void testSocket() {
//        cashBoxService.submitOrder(null);
//    }
}
