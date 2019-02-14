package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ads");
            return createAdsFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Long id, String title, String des) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description, created) VALUES (?, ?, ?, (SELECT NOW()))";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.setString(2, title);
            stmt.setString(3, des);
            System.out.println(stmt);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public void update(Ad ad) {
        try {
            String insertQuery = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getDescription());
            stmt.setLong(3, ad.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating ad.", e);
        }
    }

    @Override
    public void update(long adId, String title, String description) {
      String insertQuery = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
      try {
          PreparedStatement stmt = connection.prepareStatement(insertQuery);
          stmt.setString(1, title);
          stmt.setString(2, description);
          stmt.setLong(3, adId);
          stmt.executeUpdate();
      }catch (SQLException e){
          throw new RuntimeException("Error updating ad", e);
      }
    }

    @Override
    public void updateTitle(long adId, String title) {
        String insertQuery = "UPDATE ads SET title = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, title);
            stmt.setLong(2, adId);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error updating ad title", e);
        }
    }

    @Override
    public void updateDescription(long adId, String des) {
        String insertQuery = "UPDATE ads SET description = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, des);
            stmt.setLong(2, adId);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error updating ad description", e);
        }
    }

    @Override
    public void updateCategories(long adId, long catId) {
        String insertQuery = "INSERT INTO ad_categories (ad_id, categories_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setLong(1, adId);
            stmt.setLong(2, catId);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error updating ad categories", e);
        }
    }


    @Override
    public void delete(String id) {
        try {
            String insertQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad based on id.", e);
        }
    }

    @Override
    public Ad oneAd(Long id) {
        String query = "SELECT * FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs =stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error finding an ad on id", e);
        }
    }

    // shows ads based on ad_id
    @Override
    public Ad oneAd(String id) {
        String query = "SELECT * FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error finding an ad on id", e);
        }
    }

    //shows ads based on user_id
    @Override
    public List<Ad> userAds(long user_id){
        String query = "SELECT * FROM ads WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            return createAdsFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error finding all ads of user", e);
        }
    }

    // shows ads liked based on user_id
    @Override
    public List<Ad> likedAds(long user_id){
        String query = "SELECT * FROM vote_ad v JOIN ads ON ads.id = v.ad_id where v.user_id = ? and v.direction = 'up' and v.vote >= NOW() - INTERVAL 1 DAY;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            return createAdsFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error finding all liked ads of user", e);
        }
    }

    @Override
    public List<Ad> getByCategory(Long categoryID) {
        String query = "SELECT a.* FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id WHERE ac.categories_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, categoryID);
            return createAdsFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error get ads by category", e);
        }
    }

    @Override
    public List<Ad> getByMultipleCategory(List<Long> categoryID) {
        String query = "";
        String hold = "";
        try {
            int index = 0;
            for (Long catId: categoryID){
                index++;
                if (index == 3){
                    query = "SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id JOIN ad_categories c on a.id = c.ad_id " +
                            "WHERE " + hold + " AND c.categories_id = " + catId;
                }
                if (index == 2) {
                    query = "SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id WHERE " + hold;
                    query += " AND b.categories_id = "+ catId;
                    hold += " AND b.categories_id = "+ catId;
                }if(index == 1) {
                    query = "SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id WHERE ";
                    query += "ac.categories_id = " + catId;
                    hold += "ac.categories_id = " + catId;
                }
            }
            PreparedStatement stmt = connection.prepareStatement(query);
            return createAdsFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error get ads by multiple categories", e);
        }
    }

    @Override
    public List<Ad> searchAds(String user_search) {
        String query = "SELECT * FROM ads Where title LIKE ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + user_search + "%");
            return createAdsFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error finding all ads of the users search", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getTimestamp("created"),
            getAdCategories(id),
            getUpvotes(id),
            getDownvotes(id)
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    private List<String> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<String> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(rs.getString("category"));
        }
        return categories;
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

    private List<String> getAdCategories(Long id) throws SQLException {
        String query = "SELECT category_name AS category FROM categories c JOIN ad_categories ac ON c.id = ac.categories_id WHERE ad_id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        return createCategoriesFromResults(stmt.executeQuery());
    }

    private long getUpvotes(Long id) throws SQLException {
        String query = "SELECT * FROM vote_ad WHERE ad_id = ? AND direction = 'up'";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        return addUpVotes(stmt.executeQuery());
    }

    private long getDownvotes(Long id) throws SQLException {
        String query = "SELECT * FROM vote_ad WHERE ad_id = ? AND direction = 'down'";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setLong(1, id);
        return addDownVotes(stmt.executeQuery());
    }
}
