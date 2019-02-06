package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userSearch = request.getParameter("Search");
//        List<Ad> ads = DaoFactory.getAdsDao().all();
//        List<Ad> resultAd = new ArrayList<>();
//        for (Ad ad: ads) {
//            if (ad.getTitle().contains(userSearch)){
//                System.out.println(ad.getTitle());
//                System.out.println(userSearch);
//                resultAd.add(ad);
//                System.out.println(resultAd.size());
//            }
//        }
//        System.out.println(resultAd);
        request.setAttribute("ads", DaoFactory.getAdsDao().searchAds(userSearch));

        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }
}