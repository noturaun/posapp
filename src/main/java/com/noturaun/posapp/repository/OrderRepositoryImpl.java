package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Order;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository{

    private HikariDataSource dataSource;
    private String sql;

    @Override
    public Order[] showAll() {
        sql = "SELECT * FROM order_details;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Order> orderList = new ArrayList<>();
            while (result.next()){
                Order order = new Order();
                order.setId(result.getInt("orderId"));
                order.setOutletId(result.getInt("outledId"));
                order.setCustomerId(result.getInt("productId"));
                order.setEmployeeId(result.getInt("quantity"));
                order.setPaymentId(result.getInt("paymentId"));
                order.setPromotionId(result.getInt("promotionId"));
                orderList.add(order);
            }
            return orderList.toArray(new Order[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void getOrder(Integer orderId) {

    }

    @Override
    public void deleteOrder(Integer orderId) {

    }

    @Override
    public void editOrder(Integer orderId, Order changes) {

    }


}
