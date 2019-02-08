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
    //delete an existing ad
    void delete(String id);
    //get one ad based on id
    Ad oneAd(String id);
    List<Ad> userAds(long user_id);
    List<Ad> searchAds(String user_search);
}
