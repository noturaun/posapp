package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Customer;
import com.noturaun.posapp.repository.CustomerRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest extends ConnectionTest {
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        customerRepository = new CustomerRepositoryImpl(dataSource);
    }

    @Test
    void testCreateCustomerObj() {
        Customer customer = new Customer();
        customer.setFirstName("Muhammad");
        customer.setLastName("Syahrul");
        customer.setAddress("Sentul");

        assertEquals("Muhammad", customer.getFirstName());
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Muhammad");
        customer.setLastName("Syahrul");
        customer.setAddress("Sentul");

        customerRepository.create(customer);
//        assertEquals("Muhammad", customer.getFirstName());
    }

    @Test
    void testBulkAdd() {
        Customer customer = new Customer();
        for (int i = 0; i < 10; i++) {
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setAddress(faker.address().streetAddress());

            customerRepository.create(customer);
        }
        assertNotNull(customerRepository.get(2).getLastName());
    }

    @Test
    void testGet() {
        assertEquals("Syahrul", customerRepository.get(1).getLastName());
    }

    @Test
    void testGetAll() {
        String format = """
                   | firstName | lastName | address |
                   | %8s | %10s | %3s |
                    ------------------------------
                    """;
        for (var customer : customerRepository.getAll()) {
            System.out.printf(format, customer.getFirstName(), customer.getLastName(), customer.getAddress());
        }
    }

    @Test
    void testUpdate() {
        Customer customer = new Customer();
        customer.setFirstName("Muhammad");
        customer.setLastName("Syahrul");
        customer.setAddress("Bogor");

        customerRepository.update(1,customer);
        assertEquals(customer.getAddress(), customerRepository.get(1).getAddress());
    }

    @Test
    void testDelete() {
        customerRepository.delete(2);
        assertNull(customerRepository.get(2).getFirstName());
    }
}
