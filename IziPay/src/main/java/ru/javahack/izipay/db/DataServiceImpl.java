package ru.javahack.izipay.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.javahack.izipay.pojo.Product;
import ru.javahack.izipay.pojo.ProductCategory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@Profile("Prod")
public class DataServiceImpl implements DataService {

    @Autowired
    private MongoOperations mongoOperation;

    public ProductCategory addCategory(ProductCategory category) {
        category.setId(generateSequence(ProductCategory.SEQUENCE_NAME));
        return mongoOperation.save(category);
    }

    @Override
    public Product getProduct(long productId) {
        Query query = new Query(Criteria.where("id").is(productId));
        return mongoOperation.findOne(query, Product.class);
    }

    public Product addProduct(Product product) {
        product.setId(generateSequence(Product.SEQUENCE_NAME));
        return mongoOperation.save(product);
    }

    public List<Product> getAllProducts(long userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoOperation.findAll(Product.class);
    }

    public List<ProductCategory> getAllCategories(long userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoOperation.find(query, ProductCategory.class);
    }

    private long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperation.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
