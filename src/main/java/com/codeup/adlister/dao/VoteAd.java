package com.codeup.adlister.dao;

import com.codeup.adlister.models.voteAd;

import java.util.List;

public interface VoteAd {
    List<voteAd> all();
    List<voteAd> allByAdId(Long ad_id);
    List<voteAd> allByUserId(Long user_id);
    Boolean insert(Long adId, Long userId, String direction);
    Boolean delete(Long adId, Long user_id);
    void update(Long adId, Long userId, String direction);
}
