package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.OrderDetail;
import com.noturaun.posapp.entity.Product;
import com.noturaun.posapp.repository.OrderDetailRepositoryImpl;
import com.noturaun.posapp.repository.ProductRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

public class OrderDetailTest extends ConnectionTest {
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        productRepository = new ProductRepositoryImpl(dataSource);
        orderDetailRepository = new OrderDetailRepositoryImpl(dataSource);
    }

    @Test
    void testCreateOrderDetailObj() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1);
        orderDetail.setProductId(productRepository.get(1).getId());
        orderDetail.setQty(10);

        System.out.println(orderDetail.getProductId());
    }

    @Test
    void testCreateOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1);
        orderDetail.setProductId(productRepository.get(1).getId());
        orderDetail.setQty(10);

        orderDetailRepository.create(orderDetail);
    }

    @Test
    void testCreateOrder() {
        OrderDetail orderDetail = new OrderDetail();
        for (int i = 0; i < 10; i++) {
            orderDetail.setOrderId(faker.number().numberBetween(1, 15));
            orderDetail.setProductId(faker.number().numberBetween(1, 11));
            orderDetail.setQty(Integer.valueOf(faker.number().digits(1)));

            orderDetailRepository.create(orderDetail);
        }
    }

    @Test
    void testGet() {
        Integer productId = orderDetailRepository.getByOrderId(1).getProductId();
        Product product = productRepository.get(productId);

        System.out.println(product.getName());
    }

    @Test
    void testGetAll() {
        String format = """
                   | Order ID | Product ID | Qty |
                   | %8s | %10s | %3s |
                    ------------------------------
                    """;
        for (var orderDetail : orderDetailRepository.getAll()) {
            System.out.printf(format,orderDetail.getOrderId(), orderDetail.getProductId(), orderDetail.getQty());
        }
    }

    @Test
    void testUpdateOrderDetail() {
        orderDetailRepository.update(0, 1,19);
    }

    @Test
    void testDelete() {
        orderDetailRepository.delete(1);
    }
}
