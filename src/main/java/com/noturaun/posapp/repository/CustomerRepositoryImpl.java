package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Customer;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class CustomerRepositoryImpl implements CustomerRepository{

    private HikariDataSource dataSource;
    private String sql;


    public CustomerRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Customer[] getAll() {
        sql = "SELECT * FROM customers;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Customer> customers = new ArrayList<>();
            while (result.next()){
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setFirstName(result.getString("firstName"));
                customer.setLastName(result.getString("lastName"));
                customer.setAddress(result.getString("address"));
                customers.add(customer);
            }
            return customers.toArray(new Customer[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Customer get(Integer customerId) {
        sql = "SELECT * FROM customers where id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,customerId);
            ResultSet result = statement.executeQuery();
            Customer customer = new Customer();

            while (result.next()){
                customer.setId(result.getInt("id"));
                customer.setFirstName(result.getString("firstName"));
                customer.setLastName(result.getString("lastName"));
                customer.setAddress(result.getString("address"));
            }

            return customer;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void create(Customer customer) {
        sql = "INSERT INTO customers(firstName, lastName, address) values(?,?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer customerId, Customer changes) {
        sql = """
                UPDATE customers
                SET firstName = ?,
                    lastName = ?,
                    address = ?
                WHERE id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, changes.getFirstName());
            statement.setString(2, changes.getLastName());
            statement.setString(3, changes.getAddress());
            statement.setInt(4, customerId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer customerId) {
        String table = "customers";
        String clause = "id";

        if (isExist(dataSource, customerId, table, clause)){
            sql = " DELETE FROM " + table +" WHERE id = ? ;";
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, customerId);
                statement.executeUpdate();
            } catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }
}
