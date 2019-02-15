package com.codeup.adlister.dao;

import com.codeup.adlister.dao.Interfaces.Ads;
import com.codeup.adlister.models.Ad;

import java.util.List;

public class ListAdsDao implements Ads {
    private List<Ad> ads = null;

    public List<Ad> all() {
        if (ads == null) {
//            ads = generateAds();
        }
        return ads;
    }

    @Override
    public Long insert(Long id, String title, String des) {
        return null;
    }

    public Long insert(Ad ad) {
        // make sure we have ads
        if (ads == null) {
//            ads = generateAds();
        }
        ad.setId((long) ads.size());
        ads.add(ad);
        return ad.getId();
    }

    @Override
    public void update(Ad ad) {
    }

    @Override
    public void update(long adId, String title, String description) {

    }

    @Override
    public void updateTitle(long adId, String title) {

    }

    @Override
    public void updateDescription(long adId, String des) {

    }

    @Override
    public void updateCategories(long adId, long catId) {

    }

    @Override
    public void delete(String id) {
    }

    @Override
    public Ad oneAd(Long id) {
        return null;
    }

    @Override
    public Ad oneAd(String id) {
        return null;
    }

    @Override
    public List<Ad> getByCategory(Long categoryID) {
        return null;
    }

    @Override
    public List<Ad> getByMultipleCategory(List<Long> categoryID) {
        return null;
    }

    @Override
    public List<Ad> userAds(long user_id) {
        return null;
    }

    @Override
    public List<Ad> likedAds(long user_id) {
        return null;
    }

    @Override
    public List<Ad> searchAds(String user_search) {
        return null;
    }

//    private List<Ad> generateAds() {
//        List<Ad> ads = new ArrayList<>();
//        ads.add(new Ad(
//            1,
//            1,
//            "playstation for sale",
//            "This is a slightly used playstation",
//                "hi"
//        ));
//        ads.add(new Ad(
//            2,
//            1,
//            "Super Nintendo",
//            "Get your game on with this old-school classic!"
//        ));
//        ads.add(new Ad(
//            3,
//            2,
//            "Junior Java Developer Position",
//            "Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript"
//        ));
//        ads.add(new Ad(
//            4,
//            2,
//            "JavaScript Developer needed",
//            "Must have strong Java skills"
//        ));
//        return ads;
//    }
}
