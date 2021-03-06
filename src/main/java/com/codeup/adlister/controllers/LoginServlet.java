package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.admin;
import com.codeup.adlister.util.Password;
import com.codeup.adlister.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        if (request.getSession().getAttribute("invalid") != null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);
        String redirect = (String) request.getSession().getAttribute("redirect");
        if (user == null) {
            request.getSession().setAttribute("invalid", true);
            response.sendRedirect("/login");
            return;
        }

        int level = 0;
        boolean validAdmin = Validation.isUserAdmin(user.getId());
        admin adminLevel = DaoFactory.getAdminsDao().singleAdmin(user.getId());
        if (validAdmin) {
            level = Integer.valueOf(adminLevel.getLevel());
        }

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt && redirect != null) {
            request.getSession().setAttribute("admin", adminLevel);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(redirect);
        } else if (validAttempt){
            request.getSession().setAttribute("admin", adminLevel);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        }
        else {
            request.getSession().setAttribute("invalid", true);
            response.sendRedirect("/login");
        }
    }
}
