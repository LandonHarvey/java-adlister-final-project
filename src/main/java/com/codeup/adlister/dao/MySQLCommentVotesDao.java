package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.CommentVote;
import com.codeup.adlister.models.commentVote;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCommentVotesDao implements CommentVote {
    private Connection connection = null;

    public MySQLCommentVotesDao(Config config) {
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

    // selects all votes in the database regardless of user or ad
    @Override
    public List<commentVote> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM vote_ad");
            return createAdVotesFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error pulling all votes!", e);
        }
    }

    // select all votes belonging to an ad (both down and up)
    @Override
    public List<commentVote> allByCommentId(Long comment_id) {
        String query = "SELECT * FROM vote_comment WHERE ad_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, comment_id);
            return createAdVotesFromResults(stmt.executeQuery());
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all votes based on comment_id!", e);
        }
    }

    // select all votes belonging to a user (both down and up)
    @Override
    public List<commentVote> allByUserId(Long user_id) {
        String query = "SELECT * FROM vote_comment WHERE user_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            return createAdVotesFromResults(stmt.executeQuery());
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all comment votes based on userId!", e);
        }
    }

    // select all votes given to a users ads
    @Override
    public long commentsupvoted(long user_id) {
        String query = "SELECT * FROM vote_comment v JOIN comments ON v.comment_id = comments.id where v.direction = 'up' and comments.user_id = ? and not v.user_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            stmt.setLong(2, user_id);
            ResultSet rs = stmt.executeQuery();
            long liked = 0;
            while (rs.next()){
                liked++;
            }
            return liked;
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all likes for user comments", e);
        }
    }

    // insert a vote
    @Override
    public Boolean insert(Long comment_id, Long userId, String direction) {
        String query = "INSERT INTO vote_comment (comment_id, user_id, direction) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, comment_id);
            stmt.setLong(2, userId);
            stmt.setString(3, direction);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting comment vote", e);
        }
    }

    // update a users vote
    @Override
    public void update(Long comment_id, Long userId, String direction){
        String query = "UPDATE vote_comment SET direction = ? WHERE comment_id = ? AND user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, direction);
            stmt.setLong(2, comment_id);
            stmt.setLong(3, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating comment vote", e);
        }
    }

    //deletes a vote from a certain ad
    @Override
    public Boolean delete(Long comment_id, Long user_id) {
        String query = "DELETE FROM vote_comment  WHERE comment_id = ? AND user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, comment_id);
            stmt.setLong(2, user_id);
            return stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException("Error deleting a vote by comment_id, user_id");
        }
    }

    // extracts a vote
    private commentVote extractVote(ResultSet rs) throws SQLException {
        return new commentVote(
                rs.getLong("comment_id"),
                rs.getLong("user_id"),
                rs.getString("direction")
        );
    }

    // returns a list of votes based on result set
    private List<commentVote> createAdVotesFromResults(ResultSet rs) throws SQLException {
        List<commentVote> ac = new ArrayList<>();
        while (rs.next()) {
            ac.add(extractVote(rs));
        }
        return ac;
    }
}
