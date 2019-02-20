package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.offense;
import com.codeup.adlister.models.report;

import java.util.List;

public interface Report {
    List<report> all();
    List<report> allCommentReports();
    List<report> allUserReports();
    List<report> allAdReports();
    report byReportID(Long report_id, String type);
    List<report> byOffenseID(Long categoryId);
    List<report> byUserID(Long user_reported_Id);
    List<report> byCommentID(Long commentId);
    List<report> byAdID(Long adId);
    Boolean insertCommentReport(Long user_id, Long offense, Long comment_id, String description);
    Boolean insertAdReport(Long user_id, Long offense, Long ad_id, String description);
    Boolean insertUserReport(Long user_id, Long offense, Long user_reported_id, String description);
    Boolean delete(Long reportId);
}
