package com.codeup.adlister.dao;

import com.codeup.adlister.models.adCategories;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements AdCategories{
    private Connection connection = null;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<adCategories> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ad_categories");

            return createAdCategoryFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<adCategories> byCategoryID(Long categoryID) {
        return null;
    }

    @Override
    public List<adCategories> byAdID(Long adID) {
        return null;
    }

    @Override
    public Boolean insert(Long adID, Long categoryID) {
        String query = "INSERT INTO ad_categories (ad_id, categories_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adID);
            stmt.setLong(2, categoryID);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting ad_category", e);
        }
    }

    @Override
    public Boolean delete(Long adID, Long categoryID) {
        return null;
    }

    private adCategories extractAdCategory(ResultSet rs) throws SQLException {
        return new adCategories(
                rs.getLong("ad_id"),
                rs.getLong("category_id")
        );
    }

    private List<adCategories> createAdCategoryFromResults(ResultSet rs) throws SQLException {
        List<adCategories> ac = new ArrayList<>();
        while (rs.next()) {
            ac.add(extractAdCategory(rs));
        }
        return ac;
    }
}
