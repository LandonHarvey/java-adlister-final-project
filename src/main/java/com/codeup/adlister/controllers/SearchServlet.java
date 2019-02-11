package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.adCategories;

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
        request.setAttribute("ads", DaoFactory.getAdsDao().searchAds(userSearch));
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] categories = request.getParameterValues("categoryname");
        List<Long> cats = new ArrayList<>();
        for (String name: categories){
            cats.add(Long.parseLong(name));
        }
        request.setAttribute("catSearch", DaoFactory.getAdsDao().getByMultipleCategory(cats));
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }
}