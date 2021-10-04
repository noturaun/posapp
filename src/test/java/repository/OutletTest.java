package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Outlet;
import com.noturaun.posapp.repository.OrderDetailRepositoryImpl;
import com.noturaun.posapp.repository.OutletRepositoryImpl;
import com.noturaun.posapp.repository.ProductRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class OutletTest extends ConnectionTest {
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        outletRepository = new OutletRepositoryImpl(dataSource);
        productRepository = new ProductRepositoryImpl(dataSource);
        orderDetailRepository = new OrderDetailRepositoryImpl(dataSource);
    }

    @Test
    void testCreateOutletObj() {
        Outlet outlet = new Outlet();
        outlet.setName("XXXX");
        outlet.setAddress("Sentul");
        outlet.setPhone("085212345678");

        Assertions.assertEquals("Sentul", outlet.getAddress());
    }

    @Test
    void testCreateOutlet() {
        Outlet outlet = new Outlet();
        outlet.setName("XXXX");
        outlet.setAddress("Sentul");
        outlet.setPhone("085212345678");

        outletRepository.add(outlet);
    }


    @Test
    void testBulkAdd() {
        Outlet outlet = new Outlet();
        for (int i = 0; i < 10; i++) {
            outlet.setName(faker.company().name());
            outlet.setAddress(faker.address().fullAddress());
            outlet.setPhone(faker.phoneNumber().phoneNumber());

            outletRepository.add(outlet);
        }

    }

    @Test
    void testGet() {
        assertEquals("XXXX", outletRepository.get(1).getName());
    }

    @Test
    void testGetAll() {
        String format = """
                   | Name | Address | Phone |
                   | %8s | %10s | %3s |
                    ------------------------------
                    """;
        assertNotNull(outletRepository.getAll());
        for (var outlet : outletRepository.getAll()) {
            System.out.printf(format, outlet.getName(), outlet.getAddress(), outlet.getPhone());
        }


    }

    @Test
    void testUpdate() {
        Outlet outlet = new Outlet();
        outlet.setName("Warung");
        outlet.setAddress("Sentul");
        outlet.setPhone("085212345678");

        outletRepository.update(1, outlet);
        assertEquals("Warung", outletRepository.get(1).getName());
    }

    @Test
    void testDelete() {
        outletRepository.delete(11);
        assertNull(outletRepository.get(11).getName());
    }
}
