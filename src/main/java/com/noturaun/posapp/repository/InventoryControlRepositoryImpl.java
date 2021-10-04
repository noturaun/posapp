package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.InventoryControl;
import com.noturaun.posapp.entity.Outlet;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class InventoryControlRepositoryImpl implements InventoryControlRepository{

    private HikariDataSource dataSource;
    private String sql;

    public InventoryControlRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public InventoryControl[] getAll() {
        sql = "SELECT * FROM inventory_control;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<InventoryControl> inventoryControlList = new ArrayList<>();
            while (result.next()){
                InventoryControl inventoryControl = new InventoryControl();
                inventoryControl.setProductId(result.getInt("product_id"));
                inventoryControl.setOutletId(result.getInt("outlet_id"));
                inventoryControl.setQty(result.getInt("quantity"));
                inventoryControlList.add(inventoryControl);
            }
            return inventoryControlList.toArray(new InventoryControl[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public InventoryControl get(Integer productId) {
        sql = "SELECT * FROM inventory_control WHERE product_id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            InventoryControl inventoryControl = new InventoryControl();

            while (result.next()){
                inventoryControl.setProductId(result.getInt("product_id"));
                inventoryControl.setOutletId(result.getInt("outlet_id"));
                inventoryControl.setQty(result.getInt("quantity"));
            }
            return inventoryControl;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void add(InventoryControl inventoryControl) {
        sql = "INSERT INTO inventory_control(product_id, outlet_id, quantity) values(?,?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inventoryControl.getProductId());
            statement.setInt(2, inventoryControl.getOutletId());
            statement.setInt(3, inventoryControl.getQty());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void update(Integer productId, Integer newQuantity) {
        sql = """
                UPDATE inventory_control
                SET quantity = ?
                WHERE product_id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean delete(Integer productId) {
        String table = "inventory_control";
        String clause = "product_id";

        if (isExist(dataSource, productId, table, clause)){
            sql = " DELETE FROM " + table +" WHERE "+ clause +" = ? ;";
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
