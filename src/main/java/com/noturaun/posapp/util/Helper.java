package com.noturaun.posapp.util;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {
    public static boolean isExist(HikariDataSource dataSource, Integer recordId, String table, String clause){

        String sql = "SELECT * FROM " + table +" WHERE "+ clause +" = ? ;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recordId);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next()){
                    return true;
                } else return false;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
