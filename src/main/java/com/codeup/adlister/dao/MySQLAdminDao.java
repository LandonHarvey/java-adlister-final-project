package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.Admin;
import com.codeup.adlister.models.admin;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdminDao implements Admin {
    private Connection connection = null;

    public MySQLAdminDao(Config config) {
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
    public List<admin> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM admins");
            return createAdminsFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    // is the user an admin
    @Override
    public admin isAdmin(long id) {
        try {
            String query = "SELECT * FROM admins WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return extractAdmin(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving admin level of user if any.", e);
        }
    }

    //insert new admin must have jedimaster
    @Override
    public boolean insert(long jedimaster, long user_id, String level) {
        String query = "INSERT INTO admins (jedimaster, user_id, level) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, jedimaster);
            stmt.setLong(2, user_id);
            stmt.setString(3, level);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment", e);
        }
    }

    //delete admin rights from user
    @Override
    public void delete(String id) {
        try {
            String insertQuery = "DELETE FROM admins WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad based on id.", e);
        }
    }

    @Override
    public void update(admin admin) {
    }

    @Override
    public boolean updateLevel(long jedimaster, long user_id, String level) {
        String query = "UPDATE admins SET jedimaster = ?, level = ? WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, jedimaster);
            stmt.setString(3, level);
            stmt.setLong(2, user_id);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment", e);
        }
    }

    private admin extractAdmin(ResultSet rs) throws SQLException{
        return new admin(
                rs.getLong("jedimaster"),
                rs.getLong("user_id"),
                rs.getString("level"),
                rs.getString("password")
        );
    }

    private List<admin> createAdminsFromResults(ResultSet rs) throws SQLException {
        List<admin> c = new ArrayList<>();
        while (rs.next()) {
            c.add(extractAdmin(rs));
        }
        return c;
    }
}
