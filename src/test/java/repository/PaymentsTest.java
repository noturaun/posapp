package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Payment;
import com.noturaun.posapp.repository.PaymentMethodRepositoryImpl;
import com.noturaun.posapp.repository.PaymentRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentsTest extends ConnectionTest {
    Faker faker = new Faker();
    Payment payment = new Payment();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        paymentMethodRepository = new PaymentMethodRepositoryImpl(dataSource);
        paymentRepository = new PaymentRepositoryImpl(dataSource);
    }

    @Test
    void testCreatePaymentObj() {
        payment.setPaymentMethodId(paymentMethodRepository.get(1).getId());
        payment.setStatus("");

        assertEquals(1,payment.getPaymentMethodId());
    }

    @Test
    void testCreatePayment() {
        payment.setPaymentMethodId(paymentMethodRepository.get(2).getId());
        payment.setStatus("uncompleted");

        paymentRepository.create(payment);
    }

    @Test
    void testGet() {
        assertEquals(1, paymentRepository.get(1).getPaymentMethodId());
    }

    @Test
    void testGetAll() {
        String format = """
                     id | status | payment_method_id |
                     %s | %-9s | %s
                    ---------------------------------------------
                    """;
        for (var payment : paymentRepository.getAll()) {
            System.out.printf(format, payment.getId(), payment.getStatus(), payment.getPaymentMethodId());
        }
    }

    @Test
    void testUpdate() {
        payment.setStatus("Completed");
        payment.setPaymentMethodId(paymentRepository.get(1).getPaymentMethodId());
        paymentRepository.update(1, payment);
        assertEquals("Completed", paymentRepository.get(1).getStatus());
    }
}
