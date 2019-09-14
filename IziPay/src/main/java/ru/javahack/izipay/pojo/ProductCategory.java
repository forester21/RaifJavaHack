package ru.javahack.izipay.pojo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author FORESTER21
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ProductCategory {

    private long id;
    private String name;
}