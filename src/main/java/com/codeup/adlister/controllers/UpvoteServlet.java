package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpvoteServlet", urlPatterns = "/upvote")
public class UpvoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String redirect = request.getParameter("redirect");
        if (request.getSession().getAttribute("user") == null && redirect != null) {
            request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("/login");
            return;
        }else if (request.getSession().getAttribute("user") == null){
            request.getSession().setAttribute("redirect", "/ads");
            response.sendRedirect("/login");
            return;
        }

        Long adId = Long.parseLong(request.getParameter("adid"));
        User user = (User) request.getSession().getAttribute("user");
        String up = request.getParameter("up");
        DaoFactory.getAdVotesDao().delete(adId,user.getId());
        DaoFactory.getAdVotesDao().insert(adId,user.getId(),up);

        if (redirect == null){
            response.sendRedirect("/ads");
            return;
        }
        if (redirect.contains("Individual")){
            response.sendRedirect(redirect);
        }
    }
}
