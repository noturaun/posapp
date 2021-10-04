package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Payment;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class PaymentRepositoryImpl implements PaymentRepository{

    private HikariDataSource dataSource;
    private String sql;

    public PaymentRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Payment[] getAll() {
        sql = "SELECT * FROM payments;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Payment> payments = new ArrayList<>();
            while (result.next()){
                Payment payment = new Payment();
                payment.setId(result.getInt("id"));
                payment.setPaymentMethodId(result.getInt("payment_method_id"));
                payment.setStatus(result.getString("status"));
                payments.add(payment);
            }
            return payments.toArray(new Payment[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Payment get(Integer paymentId) {
        sql = "SELECT * FROM payments where id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,paymentId);
            ResultSet result = statement.executeQuery();
            Payment payment = new Payment();

            while (result.next()){
                payment.setId(result.getInt("id"));
                payment.setPaymentMethodId(result.getInt("payment_method_id"));
                payment.setStatus(result.getString("status"));
            }

            return payment;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void create(Payment payment) {
        sql = "INSERT INTO payments(payment_method_id, status) values(?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, payment.getPaymentMethodId());
            statement.setString(2, payment.getStatus());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer paymentId, Payment changes) {
        sql = """
                UPDATE payments
                SET status = ?,
                    payment_method_id = ?
                WHERE id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, changes.getStatus());
            statement.setInt(2, changes.getPaymentMethodId());
            statement.setInt(3, paymentId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer paymentId) {
        String table = "payments";
        String clause = "id";

        if (isExist(dataSource, paymentId, table, clause)){
            sql = " DELETE FROM " + table +" WHERE id = ? ;";
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, paymentId);
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
