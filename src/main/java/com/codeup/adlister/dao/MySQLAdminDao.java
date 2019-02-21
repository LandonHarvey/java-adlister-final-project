package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.Admin;
import com.codeup.adlister.models.admin;
import com.codeup.adlister.util.Password;
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

    // String Version
    @Override
    public List<admin> allString() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT u.username AS 'jedimaster', u2.username, a.level AS 'level' FROM admins AS a\n" +
                    "JOIN users u on a.jedimaster = u.id\n" +
                    "JOIN users u2 on a.user_id = u2.id");
            return createStringAdminsFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all admins.", e);
        }
    }

    // is the user an admin
    @Override
    public boolean isAdmin(long id) {
        try {
            String query = "SELECT * FROM admins WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving admin level of user if any.", e);
        }
    }

    @Override
    public admin singleAdmin(long id) {
        try {
            String query = "SELECT * FROM admins WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return extractAdminWithoutSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving admin level of user if any.", e);
        }
    }

    //insert new admin must have jedimaster
    @Override
    public boolean insert(admin admin) {
        String query = "INSERT INTO admins (jedimaster, user_id, level, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, admin.getJedimaster());
            stmt.setLong(2, admin.getUser_id());
            stmt.setString(3, admin.getLevel());
            stmt.setString(4, admin.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Admin", e);
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
    private admin extractAdminWithoutSet(ResultSet rs) throws SQLException{
        return new admin(
                rs.getLong("user_id"),
                rs.getString("level"),
                rs.getString("password")
        );
    }

    private admin extractStringAdmin(ResultSet rs) throws SQLException{
        return new admin(
                rs.getString("jedimaster"),
                rs.getString("username"),
                rs.getString("level")
        );
    }

    private List<admin> createAdminsFromResults(ResultSet rs) throws SQLException {
        List<admin> c = new ArrayList<>();
        while (rs.next()) {
            c.add(extractAdmin(rs));
        }
        return c;
    }

    private List<admin> createStringAdminsFromResults(ResultSet rs) throws SQLException {
        List<admin> c = new ArrayList<>();
        while (rs.next()) {
            c.add(extractStringAdmin(rs));
        }
        return c;
    }
}
