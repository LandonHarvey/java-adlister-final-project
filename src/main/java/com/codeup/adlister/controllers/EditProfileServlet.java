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

@WebServlet(name = "controllers.EditProfileServlet", urlPatterns = "/editProfile")
public class EditProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("updateError") != null){
            request.getRequestDispatcher("/WEB-INF/editProfile.jsp").forward(request, response);
        }
        request.getSession().removeAttribute("updateError");
        request.getRequestDispatcher("/WEB-INF/editProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmpassword = request.getParameter("confirmpassword");
        User user = (User) request.getSession().getAttribute("user");
        long userid = user.getId();

        boolean validAttempt = Password.check(oldpassword, user.getPassword());

        boolean inputHasErrors = username.isEmpty()
                || Validation.stringlength(username)
                || Validation.emailCheck(email)
                || email.isEmpty();

        boolean inputsHasErrors = username.isEmpty()
                || Validation.stringlength(username)
                || Validation.emailCheck(email)
                || email.isEmpty()
                || Password.passlength(newpassword)
                || Password.passlength(confirmpassword)
                ;

        if ((confirmpassword.equals("") || newpassword.equals("") || oldpassword.equals("")) && !inputHasErrors) {
            User newUser = new User (
                    userid,
                    username,
                    email,
                    user.getPassword()
            );
            DaoFactory.getUsersDao().update(newUser);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/profile");
            return;
        }

        if (validAttempt && newpassword.equals(confirmpassword) && !inputsHasErrors){
            User newUser = new User (
                    userid,
                    username,
                    email,
                    Password.hash(newpassword)
            );
            DaoFactory.getUsersDao().update(newUser);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/profile");
            return;
        }

        request.getSession().setAttribute("updateError", true);
        response.sendRedirect("/editProfile");
    }
  }
