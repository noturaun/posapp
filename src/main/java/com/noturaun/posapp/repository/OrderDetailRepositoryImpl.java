package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.OrderDetail;
import com.noturaun.posapp.entity.Product;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class OrderDetailRepositoryImpl implements OrderDetailRepository{

    private HikariDataSource dataSource;
    private String sql;

    public OrderDetailRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public OrderDetail[] getAll() {
        sql = "SELECT * FROM order_details;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<OrderDetail> orderDetailList = new ArrayList<>();
            while (result.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(result.getInt("orderId"));
                orderDetail.setProductId(result.getInt("productId"));
                orderDetail.setQty(result.getInt("quantity"));
                orderDetailList.add(orderDetail);
            }
            return orderDetailList.toArray(new OrderDetail[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public OrderDetail getByOrderId(Integer orderId) {
        sql = "SELECT * FROM order_details where orderId = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,orderId);
            ResultSet result = statement.executeQuery();
            OrderDetail orderDetail = new OrderDetail();


            while (result.next()){
                orderDetail.setOrderId(result.getInt("orderId"));
                orderDetail.setProductId(result.getInt("productId"));
                orderDetail.setQty(result.getInt("quantity"));
            }

            return orderDetail;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public OrderDetail getByProductId(Integer productId) {
        sql = "SELECT * FROM order_details where productId = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,productId);
            ResultSet result = statement.executeQuery();
            OrderDetail orderDetail = new OrderDetail();


            while (result.next()){
                orderDetail.setOrderId(result.getInt("orderId"));
                orderDetail.setProductId(result.getInt("productId"));
                orderDetail.setQty(result.getInt("quantity"));
            }

            return orderDetail;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void create(OrderDetail orderDetail) {
        sql = "INSERT INTO order_details(orderId, productId, quantity) values(?,?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderDetail.getOrderId());
            statement.setInt(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQty());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer orderId, Integer productId, Integer qty) {
        /*
         Order detail should only change the quantity of the product.
         */
        sql = """
                UPDATE order_details
                SET quantity = ?
                WHERE orderId = ? AND productId = ?;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,qty);
            statement.setInt(2, orderId);
            statement.setInt(3, productId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean delete(Integer orderId) {
        String table = "order_details";
        String clause = "orderId";

        if (isExist(dataSource, orderId, table, clause)){
            sql = """
                DELETE FROM order_details
                WHERE orderId = ? ;
                """;
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, orderId);
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
