package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Promotion;
import com.noturaun.posapp.repository.PromotionRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;

public class PromotionTest extends ConnectionTest {

    Faker faker = new Faker();
    Promotion promotion = new Promotion();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        promotionRepository = new PromotionRepositoryImpl(dataSource);
    }

    @Test
    void testCreatePromotionObj() {
        promotion.setPromoCode("NEWYEAR2022");
        promotion.setStatus("Available");
        promotion.setDiscount(.2);


        assertEquals("Available", promotion.getStatus());
    }

    @Test
    void testCreatePromotion() {
        promotion.setPromoCode("FALL2022");
        promotion.setStatus("Available");
        promotion.setDiscount(.5);

        promotionRepository.create(promotion);
    }

    @Test
    void testGet() {
        assertEquals("FALL2022",promotionRepository.get(2).getPromoCode());
    }

    @Test
    void testGetAll() {
        String format = """
                     id | code | status | discount |
                     %s | %-9s | %-9s | %s
                    ---------------------------------------------
                    """;
        assertNotNull(promotionRepository.getAll());
        for (var promotion : promotionRepository.getAll()) {
            System.out.printf(format, promotion.getId(), promotion.getPromoCode(), promotion.getStatus(), promotion.getDiscount());
        }
    }

    @Test
    void testUpdate() {
        promotion.setPromoCode("FALL2022");
        promotion.setStatus("Expired");
        promotion.setDiscount(.5);

        promotionRepository.update(2,promotion);
        assertEquals("Expired", promotionRepository.get(2).getStatus());
    }

    @Test
    void testDelete() {
        promotionRepository.delete(2);
        assertNull(promotionRepository.get(2).getStatus());
    }
}
