package com.codeup.adlister.controllers;

import com.codeup.adlister.models.admin;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdminPageServlet", urlPatterns = "/admin")
public class AdminPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("invalid") != null) {
            System.out.println("in here");
            request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
        }
        request.getSession().setAttribute("passcodeChecked", 0);
        request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String passcode = request.getParameter("passcode");
        admin admin = (admin) request.getSession().getAttribute("admin");
        boolean validAttempt = Password.check(passcode, admin.getPassword());

        if (validAttempt){
            request.getSession().setAttribute("passcodeChecked",1);
            System.out.println("valid");
            response.sendRedirect("/admin");
        }else {
            System.out.println("invalid");
            request.getSession().setAttribute("invalid", true);
            response.sendRedirect("/admin");
        }
    }
}
