package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.PaymentMethod;
import com.noturaun.posapp.repository.PaymentMethodRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;


public class PaymentMethodTest extends ConnectionTest {
    Faker faker = new Faker();
    PaymentMethod paymentMethod = new PaymentMethod();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        paymentMethodRepository = new PaymentMethodRepositoryImpl(dataSource);
    }

    @Test
    void testCreatePaymentMethodObj() {
        paymentMethod.setId(1);
        paymentMethod.setName("Cash");
        paymentMethod.setDesc("Pembayaran Tunai");

        assertEquals("Cash", paymentMethod.getName());
    }

    @Test
    void testCreatePaymentMethod() {
        paymentMethod.setName("Cash");
        paymentMethod.setDesc("Pembayaran secara tunai");

        paymentMethodRepository.add(paymentMethod);
    }

    @Test
    void testGet() {
        assertNotNull(paymentMethodRepository.get(1));
        assertEquals("Credit Card", paymentMethodRepository.get(1).getName());
    }

    @Test
    void testGetAll() {
        String format = """
                     id | name | desc |
                     %s | %-9s | %s
                    ---------------------------------------------
                    """;

        assertNotNull(paymentMethodRepository.getAll());
        for (var paymentMethod : paymentMethodRepository.getAll()) {
            System.out.printf(format, paymentMethod.getId(), paymentMethod.getName(), paymentMethod.getDesc());
        }
    }

    @Test
    void testUpdate() {
        paymentMethod.setName("Tunai");
        paymentMethod.setDesc("Pembayaran Tunai");
        paymentMethodRepository.update(1, paymentMethod);
    }

    @Test
    void testDelete() {
        paymentMethodRepository.delete(2);
        assertNull(paymentMethodRepository.get(2).getName());
    }
}
