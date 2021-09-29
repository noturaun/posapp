package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Employee;
import com.noturaun.posapp.repository.EmployeeRepository;
import com.noturaun.posapp.repository.EmployeeRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.ConnectionTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest extends ConnectionTest {

//    HikariDataSource dataSource;
//    private EmployeeRepository employeeRepository;
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        rb = ResourceBundle.getBundle("db");
        jdbcUrl = rb.getString("jdbcUrl");
        username  = rb.getString("username");
        password = rb.getString("password");
        dataSource = ConnectionUtil.getDatasource();
        employeeRepository = new EmployeeRepositoryImpl(dataSource);
    }

    @ParameterizedTest
    @CsvSource(value = "Muhammad, Syahrul, 123456, Bogor")
    void testCreateEmployeeObj(String firstName, String lastName, String phone, String address) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phone);
        employee.setAddress(address);

        assertEquals(firstName, employee.getFirstName());
    }

    @ParameterizedTest
    @CsvSource(value = "Muhammad, Syahrul, 123456, Bogor")
    void testAddEmployee(String firstName, String lastName, String phone, String address) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phone);
        employee.setAddress(address);

        employeeRepository.add(employee);
    }

    @Test
    void testBulkAdd() {
        Employee employee = new Employee();
        for (int i = 0; i < 10; i++) {
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setPhoneNumber(faker.phoneNumber().cellPhone());
            employee.setAddress(faker.address().streetAddress());

            employeeRepository.add(employee);
        }
    }

    @Test
    void testGetAllEmployees() {

        String x = """
                     id | firstname | lastname | phone | address
                     %2s | %-9s | %-9s | %-10s | %s
                    ---------------------------------------------
                    """;
        assertNotNull(employeeRepository.getAll());
        for (var employee : employeeRepository.getAll()){
            System.out.printf(x, employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getPhoneNumber(),
                    employee.getAddress());
        }
    }

    @Test
    void testGet() {
        assertNotNull(employeeRepository.get(1));
        System.out.println(employeeRepository.get(1).getFirstName());
    }

    @Test
    void testConnection() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)){
            System.out.println("Connection Established");
        } catch (SQLException exception) {
            fail(exception);
        }
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
