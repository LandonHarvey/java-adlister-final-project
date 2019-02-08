package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //mySQLCategoryDao
        List<Category> categories = DaoFactory.getCategoriesDao().all();
        System.out.println(categories);
        request.setAttribute("categories", categories);
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String des = request.getParameter("description");
        String[] categories = request.getParameterValues("categories");

        boolean inputHasErrors = (title.isEmpty() || des.isEmpty());

        if (inputHasErrors) {
            request.setAttribute("error", "All fields are required.");
            request.getSession().setAttribute("title", title);
            request.getSession().setAttribute("description", des);
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        } else {
            System.out.println(user.getId() + title + des);
            long adid = DaoFactory.getAdsDao().insert(user.getId(), title, des);
            for (String name: categories){
                Long catId = Long.valueOf(name);
                DaoFactory.getAdCategoriesDao().insert(adid, catId);
            }
        Ad ad = new Ad(
            user.getId(),// for now we'll hardcode the user id
            request.getParameter("title"),
            request.getParameter("description")
        );
        response.sendRedirect("/ads");
        }
    }
}
