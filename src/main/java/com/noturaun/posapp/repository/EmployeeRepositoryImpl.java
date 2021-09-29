package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Employee;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{
    private HikariDataSource dataSource;
    public Employee[] employees = new Employee[10];
    private String sql;

    public EmployeeRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Employee[] getAll() {
        sql = "SELECT * FROM employees";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Employee> listOfEmployee = new ArrayList<>();
            while (result.next()){
                Employee employee = new Employee();
                employee.setId(result.getInt("id"));
                employee.setFirstName(result.getString("firstName"));
                employee.setLastName(result.getString("lastName"));
                employee.setPhoneNumber(result.getString("phone"));
                employee.setAddress(result.getString("address"));
                listOfEmployee.add(employee);
            }
            return listOfEmployee.toArray(new Employee[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Employee get(Integer employeeId) {
        sql = "SELECT * FROM employees where id = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,employeeId);
            ResultSet result = statement.executeQuery();
            Employee employee = new Employee();

            while (result.next()){
                employee.setId(result.getInt("id"));
                employee.setFirstName(result.getString("firstName"));
                employee.setLastName(result.getString("lastName"));
                employee.setPhoneNumber(result.getString("phone"));
            }

            return employee;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void add(Employee employee) {
        sql = "INSERT INTO employees(firstName, lastName, phone, address) values(?,?,?,?)";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPhoneNumber());
            statement.setString(4, employee.getAddress());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer employeeId, Employee changes) {

    }

    @Override
    public void delete(Integer employeeId) {

    }
}
