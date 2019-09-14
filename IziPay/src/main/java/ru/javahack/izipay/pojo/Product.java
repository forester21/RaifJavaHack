package ru.javahack.izipay.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author FORESTER21
 */
@Getter
@Setter
@AllArgsConstructor
@Document
public class Product {
    @Id
    private long id;
    private String name;
    private BigDecimal price;
    @DBRef
    private long categoryId;
}
