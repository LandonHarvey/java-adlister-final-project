package com.codeup.adlister.dao;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Categories categoriesDao;
    private static AdCategories adCategoriesDao;
    private static VoteAd adVoteDao;
    private static ProfilePic userProfilePicDao;
    private static Comments commentDao;
    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }

    public static ProfilePic getProfilePicDao() {
        if (userProfilePicDao == null) {
            userProfilePicDao = new MySQLProfilePicDao(config);
        }
        return userProfilePicDao;
    }

    public static Categories getCategoriesDao() {
        if (categoriesDao == null) {
            categoriesDao = new MySQLCategoryDao(config);
        }
        return categoriesDao;
    }

    public static AdCategories getAdCategoriesDao() {
        if (adCategoriesDao == null) {
            adCategoriesDao = new MySQLCategoriesDao(config);
        }
        return adCategoriesDao;
    }

    public static VoteAd getAdVotesDao() {
        if (adVoteDao == null){
            adVoteDao = new MySQLVotesDao(config);
        }
        return adVoteDao;
    }

    public static Comments getCommentDao() {
        if (commentDao == null){
            commentDao = new MySQLCommentDao(config);
        }
        return commentDao;
    }
}
