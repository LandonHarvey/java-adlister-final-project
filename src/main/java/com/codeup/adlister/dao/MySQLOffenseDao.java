package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.Offense;
import com.codeup.adlister.models.offense;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOffenseDao implements Offense {
    private Connection connection = null;

    public MySQLOffenseDao(Config config) {
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
    public List<offense> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM offense");
            return createOffenseFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }
    }

    private offense extractOffense(ResultSet rs) throws SQLException {
        return new offense(
                rs.getLong("id"),
                rs.getString("offense_name")
        );
    }

    private List<offense> createOffenseFromResults(ResultSet rs) throws SQLException {
        List<offense> offenses = new ArrayList<>();
        while (rs.next()) {
            offenses.add(extractOffense(rs));
        }
        return offenses;
    }
}
