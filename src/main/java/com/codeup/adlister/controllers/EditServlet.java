package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.EditServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateAd = request.getParameter("edit");
        request.setAttribute("updatedAd", DaoFactory.getAdsDao().oneAd(updateAd));
        request.setAttribute("editAd",updateAd);
        request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long adId = Long.parseLong(request.getParameter("adId"));
        String adtitle = request.getParameter("title");
        String addes = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(adId + "post");

        Ad ad = new Ad (
                adId,
                user.getId(),
                adtitle,
                addes
        );
        DaoFactory.getAdsDao().update(ad);

        response.sendRedirect("/ads");
    }
}
