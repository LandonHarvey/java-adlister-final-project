package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

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
}