package com.codeup.adlister.dao;

import com.codeup.adlister.models.adCategories;

import java.util.List;

public interface AdCategories {
    List<adCategories> all();
    List<adCategories> byCategoryID(Long categoryID);
    List<adCategories> byAdID(Long adID);
    Boolean insert(Long adID, Long categoryID);
    Boolean delete(Long adID, Long categoryID);
    Boolean delete(Long adID);
}