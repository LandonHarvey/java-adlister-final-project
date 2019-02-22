package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.CreateCategoryServlet", urlPatterns = "/createCategory")
public class CreateCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("redirect", "/ads/create");
            response.sendRedirect("/login");
            return;
        }
        String category = request.getParameter("newCategory");
        boolean validAttempt = category.length() > 2;

        if (validAttempt) {
            DaoFactory.getCategoriesDao().insert(category);
            response.sendRedirect("/search");
        }else {
            request.setAttribute("error", "Category must be at least 3 characters");
            request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
            request.setAttribute("newCategory", category);
            request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
        }
    }
}
