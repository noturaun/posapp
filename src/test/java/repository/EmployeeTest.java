package repository;

import com.github.javafaker.Faker;
import com.noturaun.posapp.entity.Employee;
import com.noturaun.posapp.repository.EmployeeRepositoryImpl;
import com.noturaun.posapp.util.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.ConnectionTest;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest extends ConnectionTest {

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
        assertNotNull(employeeRepository.get(3));
        System.out.println(employeeRepository.get(3).getAddress());
    }

    @Test
    void testUpdate() {
        Employee changes = new Employee();
        changes.setFirstName("Opang");
        changes.setLastName("Adit");
        changes.setPhoneNumber("0852123456");
        changes.setAddress("Tanggerang");
        employeeRepository.update(3,changes);

        Employee result = employeeRepository.get(3);
        assertNotNull(result);
        assertEquals("Tanggerang", employeeRepository.get(3).getAddress());
        System.out.printf("""
                firstname | lastname | phone | address
                    %s          %s      %s      %s
                """,
                result.getFirstName(),
                result.getLastName(),
                result.getPhoneNumber(),
                result.getAddress()
                );
    }

    @Test
    void testDelete() {
        assertTrue(employeeRepository.delete(12));
        assertNull(employeeRepository.get(12).getAddress());
    }
}
