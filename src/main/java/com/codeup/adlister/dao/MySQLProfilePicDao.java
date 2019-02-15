package com.codeup.adlister.dao;

import com.codeup.adlister.models.profilePic;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProfilePicDao implements ProfilePic {

    private Connection connection = null;

    public MySQLProfilePicDao(Config config) {
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

    // All Profile Pictures
    @Override
    public List<profilePic> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM profile_pic");
            return createProfilePicFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error pulling all profilePics!", e);
        }
    }

    // Find users Profile Pic
    @Override
    public profilePic byUserId(Long user_id) {
        String query = "SELECT * FROM profile_pic WHERE user_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
//            System.out.println(stmt.executeQuery());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractProfilePic(rs);
            }
            return null;
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling users Profile Pic based on userId!", e);
        }
    }

    // update a users vote
    @Override
    public void update(Long userId, String fileHandler){
        String query = "UPDATE profile_pic SET fileHandler = ? WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fileHandler);
            stmt.setLong(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Profile Pic", e);
        }
    }

    // Insert into Profile Pic
    @Override
    public Boolean insert(Long user_id, String fileHandler) {
        String query = "INSERT INTO profile_pic (user_id, fileHandler) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setString(2, fileHandler);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Profile Pic", e);
        }
    }

    // deletes Profile Pic
    @Override
    public Boolean delete(Long user_id) {
        String query = "DELETE FROM profile_pic WHERE user_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            return stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException("Error deleting a Profile Pic user_id");
        }
    }

    // extracts a Profile Pic
    private profilePic extractProfilePic(ResultSet rs) throws SQLException {
//        System.out.println(rs.getLong("user_id"));
        return new profilePic(
                rs.getLong("user_id"),
                rs.getString("fileHandler")
        );
    }

    // returns a list of Profile Pic based on result set
    private List<profilePic> createProfilePicFromResults(ResultSet rs) throws SQLException {
        List<profilePic> ac = new ArrayList<>();
        while (rs.next()) {
            ac.add(extractProfilePic(rs));
        }
        return ac;
    }
}
