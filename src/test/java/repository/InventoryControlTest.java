package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.InventoryControl;
import com.noturaun.posapp.repository.*;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryControlTest extends ConnectionTest {

    Faker faker = new Faker();
    InventoryControl inventoryControl = new InventoryControl();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        productRepository = new ProductRepositoryImpl(dataSource);
        outletRepository = new OutletRepositoryImpl(dataSource);
        inventoryControlRepository = new InventoryControlRepositoryImpl(dataSource);
    }

    @Test
    void testCreateInventoryControlObj() {
        inventoryControl.setProductId(productRepository.get(1).getId());
        inventoryControl.setOutletId(outletRepository.get(1).getId());
        inventoryControl.setQty(100);

        assertEquals(1, inventoryControl.getProductId());
    }

    @Test
    void testCreateInventoryControl() {
        inventoryControl.setProductId(productRepository.get(1).getId());
        inventoryControl.setOutletId(outletRepository.get(1).getId());
        inventoryControl.setQty(100);

        inventoryControlRepository.add(inventoryControl);
        assertEquals(1, inventoryControlRepository.get(1).getProductId());
    }

    @Test
    void testBulkAdd() {
        for (int i = 0; i < 10; i++) {
            inventoryControl.setProductId(productRepository.get(faker.number().numberBetween(1, 11)).getId());
            inventoryControl.setOutletId(outletRepository.get(faker.number().numberBetween(1, 11)).getId());
            inventoryControl.setQty(Integer.valueOf(faker.number().digits(3)));

            inventoryControlRepository.add(inventoryControl);
        }
    }

    @Test
    void testGet() {
        assertEquals(1, inventoryControlRepository.get(1).getProductId());
    }

    @Test
    void testGetAll() {
        String format = """
                   | Product Id | Outlet ID | Quantity |
                   | %8s | %10s | %3s |
                    ------------------------------
                    """;
        assertNotNull(inventoryControlRepository.getAll());
        for (var inventory : inventoryControlRepository.getAll()) {
            System.out.printf(format, inventory.getProductId(), inventory.getOutletId(), inventory.getQty());
        }
    }
}
