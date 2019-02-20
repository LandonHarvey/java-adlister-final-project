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

@WebServlet(name = "controllers.DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteAd = request.getParameter("delete");
        if (request.getParameter("deleteReport") != null){
            Long deleteReport = Long.parseLong(request.getParameter("deleteReport"));
            DaoFactory.getReportDao().delete(deleteReport);
        }
        if (request.getParameter("deleteComment") != null){
            Long deleteComment = Long.parseLong(request.getParameter("deleteComment"));
            DaoFactory.getCommentDao().delete(deleteComment);
        }
        DaoFactory.getAdsDao().delete(deleteAd);
        response.sendRedirect("/profile");
    }
}
