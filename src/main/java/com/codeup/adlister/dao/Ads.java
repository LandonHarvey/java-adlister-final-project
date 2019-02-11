package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Long id, String title, String des);
    // update an existing ad
    void update(Ad ad);
    //update an existing ad with different items provided
    void update(long adId, String title, String description);
    //update ad title
    void updateTitle(long adId, String title);
    //update ad description
    void updateDescription(long adId, String des);
    //update ad categories
    void updateCategories(long adId, long catId);
    //delete an existing ad
    void delete(String id);
    //get one ad based on id
    Ad oneAd(Long id);
    Ad oneAd(String id);
    List<Ad> userAds(long user_id);
    List<Ad> searchAds(String user_search);
}
