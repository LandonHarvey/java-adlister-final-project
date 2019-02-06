package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdIndividualServlet", urlPatterns = "/adIndividual")
public class AdIndividualServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adId = request.getParameter("id");
        request.setAttribute("ad", DaoFactory.getAdsDao().oneAd(adId));
        request.setAttribute("user",DaoFactory.getUsersDao().findByAdId(adId));
        request.getRequestDispatcher("/WEB-INF/ads/adIndividual.jsp").forward(request, response);
    }
}

//ads/1/edit etc...

//"ads?id="${ad.id}

//ads?id=1
//request.getParameter("id")

//reading from url pattern

