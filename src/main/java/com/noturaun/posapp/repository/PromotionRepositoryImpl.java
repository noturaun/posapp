package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Promotion;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.noturaun.posapp.util.Helper.isExist;

public class PromotionRepositoryImpl implements PromotionRepository{

    private HikariDataSource dataSource;
    private String sql;

    public PromotionRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Promotion[] getAll() {
        sql = "SELECT * FROM promotions;";
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            List<Promotion> promotionList = new ArrayList<>();
            while (result.next()){
                Promotion promotion = new Promotion();
                promotion.setId(result.getInt("id"));
                promotion.setPromoCode(result.getString("code"));
                promotion.setStatus(result.getString("status"));
                promotion.setDiscount(result.getDouble("discount"));
                promotionList.add(promotion);
            }
            return promotionList.toArray(new Promotion[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Promotion get(Integer promoId) {
        sql = "SELECT * FROM promotions WHERE id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, promoId);
            ResultSet result = statement.executeQuery();
            Promotion promo = new Promotion();

            while (result.next()){
                promo.setId(result.getInt("id"));
                promo.setPromoCode(result.getString("code"));
                promo.setStatus(result.getString("status"));
                promo.setDiscount(result.getDouble("discount"));

            }
            return promo;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void create(Promotion promotion) {
        sql = "INSERT INTO promotions(code, status, discount) values(?,?,?);";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, promotion.getPromoCode());
            statement.setString(2, promotion.getStatus());
            statement.setDouble(3, promotion.getDiscount());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Integer promotionId, Promotion changes) {
        sql = """
                UPDATE promotions
                SET code = ?,
                    status = ?,
                    discount = ?
                WHERE id = ? ;
                """;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, changes.getPromoCode());
            statement.setString(2, changes.getStatus());
            statement.setDouble(3, changes.getDiscount());
            statement.setInt(4, promotionId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer promotionId) {
        String table = "promotions";
        String clause = "id";

        if (isExist(dataSource, promotionId, table, clause)){
            sql = """
                DELETE FROM promotions
                WHERE id = ? ;
                """;
            try(Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, promotionId);
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
