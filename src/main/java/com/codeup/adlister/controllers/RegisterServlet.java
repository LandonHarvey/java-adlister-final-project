package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.codeup.adlister.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("loginError") != null) {
            request.getSession().setAttribute("loginError", true);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean inputHasErrors = username.isEmpty()
            || Validation.stringlength(username)
            || Password.passlength(password)
            || Validation.emailCheck(email)
            || email.isEmpty()
            || password.isEmpty()
            || !Validation.isExistingUser(username)
            || !Validation.isExistingEmail(email)
            || (!password.equals(passwordConfirmation));

        if (inputHasErrors) {

            if (username.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
            } else if ((!password.equals(passwordConfirmation))) {
                request.setAttribute("error", "Passwords Don't Match.");
            }else if (Validation.stringlength(username)) {
                request.setAttribute("error", "Username must be at least 5 characters.");
            } else if (Validation.emailCheck(email)){
                request.setAttribute("error", "Email Format Incorrect.");
            } else if (Password.passlength(password)) {
                request.setAttribute("error", "Password must by at least 5 characters.");
            }else if (!Validation.isExistingUser(username)) {
                request.setAttribute("error", "Username Taken");
            } else if (!Validation.isExistingEmail(email)){
                request.setAttribute("error", "Email already linked to account");
            } else {
                request.setAttribute("error", "Please check information and try again");
            }
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("password", password);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else {
            // create and save a new user
            User user = new User(username, email, password);
            DaoFactory.getUsersDao().insert(user);
            response.sendRedirect("/login");
        }
    }
}
