package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.Comments;
import com.codeup.adlister.models.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.x.json.JsonArray;

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

    //TODO: fix ad_id
    public List<Comment> allByAdId(Long ad_id) {
        String query = "SELECT * FROM comments JOIN users u on comments.user_id = u.id  WHERE ad_id = ? and parent_comment_id is null";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, ad_id);
            return createCommentsFromResults(stmt.executeQuery());
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all comments based on adId!", e);
        }
    }

    // insert a comment based on userId and adId
    @Override
    public boolean insert(Long user_id, Long ad_id, String comment) {
        String query = "INSERT INTO comments (user_id, ad_id, comment) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setLong(2, ad_id);
            stmt.setString(3, comment);
            System.out.println(stmt);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment", e);
        }
    }

    // insert a comment based on userId and adId and parent
    @Override
    public boolean insert(Long user_id, Long ad_id, Long parent_comment_id, String comment) {
        String query = "INSERT INTO comments (user_id, ad_id, parent_comment_id, comment) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setLong(2, ad_id);
            stmt.setLong(3, parent_comment_id);
            stmt.setString(4, comment);
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
        long id = rs.getLong("id");
        return new Comment(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("ad_id"),
                rs.getLong("parent_comment_id"),
                rs.getString("comment"),
                getComments(id),
                rs.getString("username"),
                rs.getTimestamp("posted"),
                getUpvotes(id),
                getDownvotes(id)
        );
    }

    private List<Comment> getComments(long id) throws SQLException{
        String query = "SELECT * FROM comments JOIN users u on comments.user_id = u.id WHERE parent_comment_id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        return createCommentsFromResults(stmt.executeQuery());
    }

    private long addUpVotes(ResultSet rs) throws SQLException {
        long upvotes = 0;
        while(rs.next()){
            upvotes++;
        }
        return upvotes;
    }

    private long addDownVotes(ResultSet rs) throws SQLException {
        long downvotes = 0;
        while(rs.next()){
            downvotes++;
        }
        return downvotes;
    }

    private long getUpvotes(Long id) throws SQLException {
        String query = "SELECT * FROM vote_comment WHERE comment_id = ? AND direction = 'up'";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        return addUpVotes(stmt.executeQuery());
    }

    private long getDownvotes(Long id) throws SQLException {
        String query = "SELECT * FROM vote_comment WHERE comment_id = ? AND direction = 'down'";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        return addDownVotes(stmt.executeQuery());
    }

    private List<Comment> createCommentsFromResults(ResultSet rs) throws SQLException {
            List<Comment> c = new ArrayList<>();
        while (rs.next()) {
            c.add(extractComment(rs));
        }
        return c;
    }
}
