package com.codeup.adlister.models;

public class adCategories {
    private Long ad_id;
    private Long category_id;

    public adCategories(Long ad_id, Long category_id) {
        this.ad_id = ad_id;
        this.category_id = category_id;
    }

    public Long getAd_id() {
        return ad_id;
    }

    public void setAd_id(Long ad_id) {
        this.ad_id = ad_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
