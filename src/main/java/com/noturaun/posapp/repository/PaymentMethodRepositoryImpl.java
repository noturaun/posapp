package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.PaymentMethod;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    private HikariDataSource dataSource;
    private String sql;

    public PaymentMethodRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public PaymentMethod[] getAll() {
        sql = "SELECT * FROM payment_methods;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<PaymentMethod> paymentMethods = new ArrayList<>();
            while (result.next()){
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(result.getInt("id"));
                paymentMethod.setName(result.getString("name"));
                paymentMethod.setDesc(result.getString("description"));
                paymentMethods.add(paymentMethod);
            }
            return paymentMethods.toArray(new PaymentMethod[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public PaymentMethod get(Integer paymentMethodId) {
        sql = "SELECT * FROM payment_methods where id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,paymentMethodId);
            ResultSet result = statement.executeQuery();
            PaymentMethod paymentMethod = new PaymentMethod();

            while (result.next()){
                paymentMethod.setId(result.getInt("id"));
                paymentMethod.setName(result.getString("name"));
                paymentMethod.setDesc(result.getString("description"));
            }

            return paymentMethod;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void add(PaymentMethod paymentMethod) {
        sql = "INSERT INTO payment_methods(name, description) values(?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, paymentMethod.getName());
            statement.setString(2, paymentMethod.getDesc());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer paymentMethodId, PaymentMethod changes) {
        sql = """
                UPDATE payment_methods
                SET name = ?,
                    description = ?
                WHERE id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, changes.getName());
            statement.setString(2, changes.getDesc());
            statement.setInt(3, paymentMethodId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer paymentMethodId) {
        String table = "payment_methods";
        String clause = "id";

        if (isExist(dataSource, paymentMethodId, table, clause)){
            sql = " DELETE FROM " + table +" WHERE id = ? ;";
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, paymentMethodId);
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
