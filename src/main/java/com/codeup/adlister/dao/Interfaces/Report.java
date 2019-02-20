package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.offense;
import com.codeup.adlister.models.report;

import java.util.List;

public interface Report {
    List<report> all();
    List<offense> byCategoryID(Long categoryID);
    List<offense> byAdID(Long adID);
    Boolean insertCommentReport(String description, Long user_id, Long offense, Long comment_id);
    Boolean insertAdReport(Long user_id, Long offense, String description, Long ad_id);
    Boolean insertUserReport(Long user_id, Long offense, Long user_reported_id, String description);
    Boolean delete(Long reportId);
}
