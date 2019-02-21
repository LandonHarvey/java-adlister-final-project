package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ReportServlet", urlPatterns = "/report")
public class ReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long inspect =Long.parseLong(request.getParameter("inspect"));
        String type = request.getParameter("type");
        if (request.getSession().getAttribute("passcodeChecked") == null){
            request.getSession().setAttribute("passcodeChecked", 0);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        if (((Integer) request.getSession().getAttribute("passcodeChecked")) == 1){
            request.setAttribute("type",type);
            request.setAttribute("Report", DaoFactory.getReportDao().byReportID(inspect,type));
            request.getRequestDispatcher("/WEB-INF/reports/report.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("passcodeChecked", 0);
        request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        User user = (User) request.getSession().getAttribute("user");
        String report = request.getParameter("report");
        Long offense = Long.parseLong(request.getParameter("offenses"));
        String type = request.getParameter("type");
        Long variable = Long.parseLong(request.getParameter("changeVariable"));
        System.out.println(report);
        System.out.println(offense);
        System.out.println(type);
        System.out.println(variable);

        if (type.equals("ad")){
            DaoFactory.getReportDao().insertAdReport(user.getId(),offense,variable, report);
            response.sendRedirect(redirect);
        }else if(type.equals("comment")) {
            DaoFactory.getReportDao().insertCommentReport(user.getId(),offense,variable,report);
            response.sendRedirect(redirect);
        } else {
            DaoFactory.getReportDao().insertUserReport(user.getId(),offense,variable,report);
            response.sendRedirect(redirect);
        }
    }
}
