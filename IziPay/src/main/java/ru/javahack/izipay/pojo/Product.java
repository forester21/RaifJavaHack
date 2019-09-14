package ru.javahack.izipay.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author FORESTER21
 */
@Getter
@Setter
@AllArgsConstructor
public class Product {

    private long id;
    private String name;
    private BigDecimal price;
    private ProductCategory productCategory;
}
