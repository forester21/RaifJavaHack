package ru.javahack.izipay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javahack.izipay.pojo.api.CountOfProducts;
import ru.javahack.izipay.pojo.api.ProductsByCategory;
import ru.javahack.izipay.util.ProductUtils;
import ru.javahack.izipay.util.TestData;

import java.util.List;

/**
 * @author FORESTER
 */
@RestController
@RequestMapping("/api")
public class CashBoxController {

    @GetMapping("/products")
    public List<ProductsByCategory> getProductList() {
        //TODO get real data
        return ProductUtils.groupProductsByCategories(TestData.products());
    }

    @PostMapping("/products")
    public void submitProducts(List<CountOfProducts> chosenProducts) {
        //TODO
        return;
    }
}
