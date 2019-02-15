package com.codeup.adlister.dao;

import com.codeup.adlister.models.Comment;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCommentDao implements Comments {
    private Connection connection = null;

    public MySQLCommentDao(Config config) {
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

    // selects all comments in the database regardless of user or ad
    @Override
    public List<Comment> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM comments");
            return createCommentsFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error pulling all votes!", e);
        }
    }

    // select all comments belonging to an ad
    @Override
    public List<Comment> allByAdId(Long ad_id) {
        String query = "SELECT * FROM comments WHERE ad_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, ad_id);
            return createCommentsFromResults(stmt.executeQuery());
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all votes based on adId!", e);
        }
    }

    // insert a comment based on userId and adId
    @Override
    public boolean insert(Long user_id, Long ad_id, String comment) {
        String query = "INSERT INTO comments (ad_id, user_id, comment) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setLong(2, ad_id);
            stmt.setString(3, comment);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment", e);
        }
    }

    // updating comment based on userId and adId
    @Override
    public void update(Long user_Id, Long ad_Id, String comment) {
        String query = "UPDATE comments SET comment = ? WHERE ad_id = ? AND user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, comment);
            stmt.setLong(2, ad_Id);
            stmt.setLong(3, user_Id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating comment", e);
        }
    }

    // delete comment based on userId and adId
    @Override
    public Boolean delete(Long user_id, Long ad_id) {
        String query = "DELETE FROM vote_ad  WHERE user_id = ? AND ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, ad_id);
            stmt.setLong(2, user_id);
            return stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException("Error deleting a vote by ad_id, user_id");
        }
    }

    private Comment extractComment(ResultSet rs) throws SQLException{
        return new Comment(
                rs.getLong("user_id"),
                rs.getLong("ad_id"),
                rs.getString("comment")
        );
    }

    private List<Comment> createCommentsFromResults(ResultSet rs) throws SQLException {
            List<Comment> c = new ArrayList<>();
        while (rs.next()) {
            c.add(extractComment(rs));
        }
        return c;
    }
}
