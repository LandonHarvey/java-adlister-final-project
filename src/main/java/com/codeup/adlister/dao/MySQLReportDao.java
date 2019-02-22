package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.Report;
import com.codeup.adlister.models.offense;
import com.codeup.adlister.models.report;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLReportDao implements Report {
    private Connection connection = null;

    public MySQLReportDao(Config config) {
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
    public List<report> all() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM report");
            return createReportFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all reports.", e);
        }
    }

    @Override
    public List<report> allAdReports() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', a.title, r.created FROM report AS r\n" +
                    "JOIN users u on r.user_id = u.id\n" +
                    "JOIN ads a on r.ad_id = a.id\n" +
                    "JOIN offense o on r.offense = o.id\n" +
                    "JOIN users u2 on a.user_id= u2.id\n" +
                    "WHERE ad_id IS NOT NULL");
            return createReportFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ad reported.", e);
        }
    }

    @Override
    public List<report> allUserReports() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', u2.id as title, r.created FROM report AS r\n" +
                    "JOIN users u on r.user_id = u.id\n" +
                    "JOIN users u2 on r.user_reported_id= u2.id\n" +
                    "JOIN offense o on r.offense = o.id\n" +
                    "WHERE user_reported_id IS NOT NULL");
            return createReportFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all users reported.", e);
        }
    }

    @Override
    public List<report> allCommentReports() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', c.comment as 'title', r.created FROM report AS r\n" +
                    "JOIN users u on r.user_id = u.id\n" +
                    "JOIN comments c on r.comment_id = c.id\n" +
                    "JOIN offense o on r.offense = o.id\n" +
                    "JOIN users u2 on c.user_id = u2.id\n" +
                    "WHERE comment_id IS NOT NULL");
            return createReportFromResults(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all comments reported", e);
        }
    }


    @Override
    public List<report> byOffenseID(Long offenseId) {
        String query = "SELECT * FROM report WHERE offense = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, offenseId);
            return createReportFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error selected report by offense Id", e);
        }
    }

    @Override
    public List<report> byUserID(Long user_reported_Id) {
        String query = "SELECT * FROM report WHERE user_reported_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_reported_Id);
            return createReportFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error selected report by user Id", e);
        }
    }

    // by Report Id
    @Override
    public report byReportID(Long report_id, String type) {
        String query;
        System.out.println("hi");
        if (type.equals("ad")){
            System.out.println(1);
            query = "SELECT a.id, u.username, o.offense_name, r.description, u2.username as 'offender', a.title, r.created FROM report AS r\n" +
                    "JOIN users u on r.user_id = u.id\n" +
                    "JOIN ads a on r.ad_id = a.id\n" +
                    "JOIN offense o on r.offense = o.id\n" +
                    "JOIN users u2 on a.user_id= u2.id\n" +
                    "WHERE r.id = ?";
        }else if(type.equals("comment")){
            System.out.println(2);
            query = "SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', c.comment as 'title', r.created FROM report AS r\n" +
                    "JOIN users u on r.user_id = u.id\n" +
                    "JOIN comments c on r.comment_id = c.id\n" +
                    "JOIN offense o on r.offense = o.id\n" +
                    "JOIN users u2 on c.user_id = u2.id\n" +
                    "WHERE r.id = ?";
        }else if (type.equals("user")){
            System.out.println(3);
            query = "SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', u2.id as title, r.created FROM report AS r\n" +
                    "JOIN users u on r.user_id = u.id\n" +
                    "JOIN users u2 on r.user_reported_id= u2.id\n" +
                    "JOIN offense o on r.offense = o.id\n" +
                    "WHERE r.id = ?";
        }else {
            return null;
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, report_id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractReport(rs);
        }catch (SQLException e){
            throw new RuntimeException("Error selecting report by report Id", e);
        }
    }

    @Override
    public List<report> byCommentID(Long commentId) {
        String query = "SELECT * FROM report WHERE comment_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, commentId);
            return createReportFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error selected report by comment Id");
        }
    }

    @Override
    public List<report> byAdID(Long AdId) {
        String query = "SELECT * FROM report WHERE ad_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, AdId);
            return createReportFromResults(stmt.executeQuery());
        }catch (SQLException e){
            throw new RuntimeException("Error selected report by ad Id");
        }
    }

    @Override
    public Boolean insertCommentReport(Long user_id, Long offense, Long comment_id, String description) {
        String query = "INSERT INTO report (user_id, offense, comment_id, description) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setLong(2, offense);
            stmt.setLong(3, comment_id);
            stmt.setString(4, description);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Comment Report", e);
        }
    }

    @Override
    public Boolean insertAdReport(Long user_id, Long offense, Long ad_id, String description) {
        String query = "INSERT INTO report (user_id, offense, ad_id, description) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setLong(2, offense);
            stmt.setLong(3, ad_id);
            stmt.setString(4, description);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting Ad Report", e);
        }
    }

    @Override
    public Boolean insertUserReport(Long user_id, Long offense, Long user_reported_id, String description) {
        String query = "INSERT INTO report (user_id, offense, user_reported_id, description) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, user_id);
            stmt.setLong(2, offense);
            stmt.setLong(3, user_reported_id);
            stmt.setString(4, description);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting User Report", e);
        }
    }


    @Override
    public Boolean delete(Long reportId) {
        try {
            String query = "DELETE FROM report WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, reportId);
            return stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting report based on id.", e);
        }
    }

    private report extractReport(ResultSet rs) throws SQLException {
        return new report(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("offense_name"),
                rs.getString("description"),
                rs.getString("offender"),
                rs.getString("title"),
                rs.getString("created")
        );
    }

    private List<report> createReportFromResults(ResultSet rs) throws SQLException {
        List<report> reports = new ArrayList<>();
        while (rs.next()) {
            reports.add(extractReport(rs));
        }
        return reports;
    }
}
