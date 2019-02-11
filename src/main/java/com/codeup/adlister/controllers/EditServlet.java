package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
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
        List<Category> categories = DaoFactory.getCategoriesDao().all();
        request.setAttribute("categories", categories);
        request.setAttribute("updatedAd", DaoFactory.getAdsDao().oneAd(updateAd));
        request.setAttribute("editAd",updateAd);
        request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long adId = Long.parseLong(request.getParameter("adId"));
        String adtitle = request.getParameter("title");
        String addes = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("user");
        Ad adOld = DaoFactory.getAdsDao().oneAd(adId);
        String[] categories = request.getParameterValues("categories");

        boolean inputHasErrors = (adtitle.isEmpty() || addes.isEmpty() || categories == null);

        if (inputHasErrors) {
            request.setAttribute("error", "All fields are Required.");
            List<Category> categoriesList = DaoFactory.getCategoriesDao().all();
            request.setAttribute("updatedAd", DaoFactory.getAdsDao().oneAd(adId));
            request.setAttribute("categories", categoriesList);
            request.setAttribute("editAd",adId);
            request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);
        }

        if (!adOld.getTitle().equals(adtitle)){
            DaoFactory.getAdsDao().updateTitle(adId, adtitle);
            System.out.println(adId);
            System.out.println("done" + adtitle);
        }

        if (!adOld.getDescription().equals(addes)){
            DaoFactory.getAdsDao().updateDescription(adId, addes);
            System.out.println(adId);
            System.out.println("done" + addes);
        }


        if (categories != null) {
            for (String name : categories) {
                Long catId = Long.valueOf(name);
                DaoFactory.getAdsDao().updateCategories(adId, catId);
                System.out.println(adId);
                System.out.println("done" + name);
            }
        }

//        Ad ad = new Ad (
//                adId,
//                user.getId(),
//                adtitle,
//                addes
//        );
//        DaoFactory.getAdsDao().update(ad);

        response.sendRedirect("/profile");
    }
}
