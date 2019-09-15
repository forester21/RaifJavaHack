package ru.javahack.izipay.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author FORESTER21
 */
@Getter
@Setter
@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    private long id;
    private String name;
    private BigDecimal price;
    private long categoryId;

    public Product(String name, BigDecimal price, long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return new StringBuilder("product {")
                .append(id)
                .append(",")
                .append(name)
                .append(",")
                .append(price)
                .append(",")
                .append(categoryId)
                .append("}")
                .toString();
    }
}
