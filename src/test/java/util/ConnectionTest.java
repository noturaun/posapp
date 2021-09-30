package util;

import com.mysql.cj.jdbc.Driver;
import com.noturaun.posapp.entity.Employee;
import com.noturaun.posapp.repository.EmployeeRepository;
import com.noturaun.posapp.repository.EmployeeRepositoryImpl;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import com.noturaun.posapp.util.ConnectionUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ConnectionTest {

    public ResourceBundle rb;
    public String jdbcUrl;
    public String username;
    public String password;
    public static HikariDataSource dataSource;
    public EmployeeRepository employeeRepository;

    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @BeforeEach
    @Disabled
    void setUp() {
//        rb = ResourceBundle.getBundle("db");
//        jdbcUrl = rb.getString("jdbcUrl");
//        username  = rb.getString("username");
//        password = rb.getString("password");
//        dataSource = ConnectionUtil.getDatasource();
//        employeeRepository = new EmployeeRepositoryImpl(dataSource);
    }

    @Test
    void testConnection() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)){
            System.out.println("Connection Established");
        } catch (SQLException exception) {
            fail(exception);
        }
    }


    @ParameterizedTest
    @Disabled
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
    @Disabled
    void testConnectionClose() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connection Established");
        } catch (SQLException exception) {
            fail(exception);
        }
    }

    @Test
    @Disabled
    void testConnectionUsingHikari() throws SQLException {
        HikariDataSource dataSource = ConnectionUtil.getDatasource();
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getMetaData());
        assertNotNull(connection.getMetaData());
        connection.close();
        dataSource.close();
    }

    @AfterAll
    static void afterAll() {
        dataSource.close();
    }
}
