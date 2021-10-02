package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Outlet;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class OutletRepositoryImpl implements OutletRepository{

    private HikariDataSource dataSource;
    private String sql;

    public OutletRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Outlet[] getAll() {
        sql = "SELECT * FROM outlets;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Outlet> outletList = new ArrayList<>();
            while (result.next()){
                Outlet outlet = new Outlet();
                outlet.setId(result.getInt("id"));
                outlet.setName(result.getString("name"));
                outlet.setPhone(result.getString("phone"));
                outlet.setAddress(result.getString("address"));
                outletList.add(outlet);
            }
            return outletList.toArray(new Outlet[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Outlet get(Integer outletId) {
        sql = "SELECT * FROM outlets WHERE id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, outletId);
            ResultSet result = statement.executeQuery();
            Outlet outlet = new Outlet();

            while (result.next()){
                outlet.setId(result.getInt("id"));
                outlet.setName(result.getString("name"));
                outlet.setAddress(result.getString("address"));
                outlet.setPhone(result.getString("phone"));

            }
            return outlet;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void add(Outlet outlet) {
        sql = "INSERT INTO outlets(name, address, phone) values(?,?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, outlet.getName());
            statement.setString(2, outlet.getAddress());
            statement.setString(3, outlet.getPhone());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer outletId, Outlet changes) {
        sql = """
                UPDATE outlets
                SET name = ?,
                    address = ?,
                    phone = ?
                WHERE id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, changes.getName());
            statement.setString(2, changes.getAddress());
            statement.setString(3, changes.getPhone());
            statement.setInt(4, outletId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer outletId) {
        String table = "outlets";
        String clause = "id";

        if (isExist(dataSource, outletId, table, clause)){
            sql = """
                DELETE FROM outlets
                WHERE id = ? ;
                """;
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, outletId);
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
