package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Employee;
import com.noturaun.posapp.entity.Product;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class ProductRepositoryImpl implements ProductRepository {

    private HikariDataSource dataSource;
    private String sql;

    public ProductRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product[] getAll() {
        sql = "SELECT * FROM products;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Product> listOfProduct = new ArrayList<>();
            while (result.next()){
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setPrice(result.getInt("price"));
                product.setDesc(result.getString("description"));
                listOfProduct.add(product);
            }
            return listOfProduct.toArray(new Product[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void add(Product product) {
//        sql = "INSERT INTO employees(firstName, lastName, phone, address) values(?,?,?,?);";
        sql = "INSERT INTO products(name, price, description) values(?,?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getDesc());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product get(Integer productId) {
        sql = "SELECT * FROM products where id = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,productId);
            ResultSet result = statement.executeQuery();
            Product product = new Product();


                while (result.next()){
                    product.setId(result.getInt("id"));
                    product.setName(result.getString("name"));
                    product.setPrice(result.getInt("price"));
                    product.setDesc(result.getString("description"));
                }

            return product;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void update(Integer productId, Product changes) {
        sql = """
                UPDATE products
                SET name = ?,
                    price = ?,
                    description = ?
                WHERE id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, changes.getName());
            statement.setInt(2, changes.getPrice());
            statement.setString(3,changes.getDesc());
            statement.setInt(4, productId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean delete(Integer productId) {
        String table = "employees";
        String clause = "id";
        if (isExist(dataSource, productId, table, clause)){
            sql = """
                DELETE FROM products
                WHERE id = ? ;
                """;
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, productId);
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
