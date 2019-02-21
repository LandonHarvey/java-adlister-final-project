package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.admin;
import com.codeup.adlister.models.report;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.AdminPageServlet", urlPatterns = "/admin")
public class AdminPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("invalid") != null) {
            request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
        }
        if (request.getSession().getAttribute("passcodeChecked") != null){
            request.setAttribute("postsReports", DaoFactory.getReportDao().allAdReports());
            request.setAttribute("commentReports", DaoFactory.getReportDao().allCommentReports());
            request.setAttribute("userReports", DaoFactory.getReportDao().allUserReports());
            request.setAttribute("adminList", DaoFactory.getAdminsDao().allString());
            request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("passcodeChecked", 0);
        request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String passcode = request.getParameter("passcode");
        admin admin = (admin) request.getSession().getAttribute("admin");
        boolean validAttempt = Password.check(passcode, admin.getPassword());
        System.out.println(admin.getLevel());

        if (validAttempt){
            request.getSession().setAttribute("passcodeChecked",1);
            System.out.println(request.getSession().getAttribute("passcodeChecked"));
            response.sendRedirect("/admin");
            return;
        }else {
            System.out.println("invalid");
            request.getSession().setAttribute("invalid", true);
            response.sendRedirect("/admin");
        }
    }
}
