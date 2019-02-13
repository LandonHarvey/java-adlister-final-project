package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DownvoteServlet", urlPatterns = "/downvote")
public class DownvoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("redirect", "/ads");
            response.sendRedirect("/login");
            return;
        }
        long adId = Long.parseLong(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        String down = request.getParameter("down");
        DaoFactory.getAdVotesDao().delete(adId,user.getId());
        DaoFactory.getAdVotesDao().insert(adId,user.getId(),down);
        response.sendRedirect("/ads");
    }
}