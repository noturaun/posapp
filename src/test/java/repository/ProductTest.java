package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Product;
import com.noturaun.posapp.repository.ProductRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest extends ConnectionTest {
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        productRepository = new ProductRepositoryImpl(dataSource);
    }

    @Test
    void testCreateProductObj() {
        Product product = new Product();
        product.setId(1);
        product.setName("Indomie");
        product.setPrice(2000);


        assertEquals("Indomie", product.getName());
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setName("Indomie");
        product.setPrice(1000);
        product.setDesc("x");


        assertEquals("Indomie", product.getName());
        productRepository.add(product);
    }

    @Test
    void testGetProduct() {
        assertNotNull(productRepository.get(1));
        assertEquals("Indomie", productRepository.get(1).getName());
    }

    @Test
    void testBulkAdd() {
        Product product = new Product();
        for (int i = 0; i < 10; i++) {
            product.setName(faker.food().ingredient());
            product.setPrice(Integer.valueOf(faker.number().digits(4)));
            product.setDesc(faker.howIMetYourMother().quote());

            productRepository.add(product);
        }
    }

    @Test
    void testGetAll() {
        String format = """
                     id | name | price | desc |
                     %s | %-9s | %-9s | %s
                    ---------------------------------------------
                    """;
        for (var product : productRepository.getAll()) {
            System.out.printf(format,product.getId(), product.getName(), product.getPrice(), product.getDesc());
        }
    }

    @Test
    void testUpdate() {
        Product changes = new Product();
        changes.setDesc("Ini soto");
        // non null coloumn should be fill with unchanged record
        changes.setName("Soto");
        changes.setPrice(15_000);
        productRepository.update(1, changes);
    }
}
