package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.VoteAd;
import com.codeup.adlister.models.voteAd;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLVotesDao implements VoteAd {
    private Connection connection = null;

    public MySQLVotesDao(Config config) {
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
    public List<voteAd> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM vote_ad");
            return createAdVotesFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error pulling all votes!", e);
        }
    }

    // select all votes belonging to an ad (both down and up)
    @Override
    public List<voteAd> allByAdId(Long ad_id) {
        String query = "SELECT * FROM vote_ad WHERE ad_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, ad_id);
            return createAdVotesFromResults(stmt.executeQuery());
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all votes based on adId!", e);
        }
    }

    // select all votes belonging to a user (both down and up)
    @Override
    public List<voteAd> allByUserId(Long user_id) {
        String query = "SELECT * FROM vote_ad WHERE user_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            return createAdVotesFromResults(stmt.executeQuery());
        }catch (SQLException e) {
            throw new RuntimeException("Error pulling all votes based on userId!", e);
        }
    }

    // select all votes given to a users ads
    @Override
    public long adsupvoted(long user_id) {
        String query = "SELECT * FROM vote_ad v JOIN ads ON ads.id = v.ad_id where v.direction = 'up' and ads.user_id = ? and not v.user_id = ?";
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
            throw new RuntimeException("Error pulling all likes for user", e);
        }
    }

    // insert a vote
    @Override
    public Boolean insert(Long adId, Long userId, String direction) {
        String query = "INSERT INTO vote_ad (ad_id, user_id, direction) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adId);
            stmt.setLong(2, userId);
            stmt.setString(3, direction);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting vote", e);
        }
    }

    // update a users vote
    @Override
    public void update(Long adId, Long userId, String direction){
        String query = "UPDATE vote_ad SET direction = ? WHERE ad_id = ? AND user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, direction);
            stmt.setLong(2, adId);
            stmt.setLong(3, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating vote", e);
        }
    }

    //deletes a vote from a certain ad
    @Override
    public Boolean delete(Long adId, Long user_id) {
        String query = "DELETE FROM vote_ad  WHERE ad_id = ? AND user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, adId);
            stmt.setLong(2, user_id);
            return stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException("Error deleting a vote by ad_id, user_id");
        }
    }

    // extracts a vote
    private voteAd extractVote(ResultSet rs) throws SQLException {
        return new voteAd(
                rs.getLong("ad_id"),
                rs.getLong("user_id"),
                rs.getString("direction")
        );
    }

    // returns a list of votes based on result set
    private List<voteAd> createAdVotesFromResults(ResultSet rs) throws SQLException {
        List<voteAd> ac = new ArrayList<>();
        while (rs.next()) {
            ac.add(extractVote(rs));
        }
        return ac;
    }
}
