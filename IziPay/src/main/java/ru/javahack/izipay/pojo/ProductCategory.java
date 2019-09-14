package ru.javahack.izipay.pojo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author FORESTER21
 */
@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "categories")
public class ProductCategory {

    public static final String SEQUENCE_NAME = "category_sequence";

    @Id
    private long id;
    private String name;
    private long userId;

    public ProductCategory(String name, long userId) {
        this.name = name;
        this.userId = userId;
    }
}