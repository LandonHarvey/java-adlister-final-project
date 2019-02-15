package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.ProfilePic;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.profilePic;
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
        request.getRequestDispatcher("/WEB-INF/editProfile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmpassword = request.getParameter("confirmpassword");
        String fileHandler = request.getParameter("fileHandler");
        User user = (User) request.getSession().getAttribute("user");
        long userid = user.getId();

        boolean validAttempt = Password.check(oldpassword, user.getPassword());

        // already the users email
        boolean usersEmail = user.getEmail().equals(email);

        boolean inputHasErrors = username.isEmpty()
                || Validation.stringlength(username)
                || Validation.emailCheck(email)
                || email.isEmpty();
        boolean inputsHasErrors = username.isEmpty()
                || Validation.stringlength(username)
                || Validation.emailCheck(email)
                || email.isEmpty()
                || Password.passlength(newpassword)
                || Password.passlength(confirmpassword);

        boolean inputPasswordEquals = confirmpassword.equals("")
                && newpassword.equals("")
                && oldpassword.equals("");

        boolean profilePicUploaded = !fileHandler.equals("");

        boolean pUploadPSEmptyUCheckECheck = profilePicUploaded && inputPasswordEquals && !inputHasErrors && (usersEmail || Validation.isExistingEmail(email));

        boolean psEmptyUCheckECheck = inputPasswordEquals && !inputHasErrors && (usersEmail || Validation.isExistingEmail(email));

        boolean vPasswordNCCheckAllFieldsCheckPNone = validAttempt && newpassword.equals(confirmpassword) &&
                !inputHasErrors && (usersEmail || Validation.isExistingEmail(email));


        // update only profile pic everything else blank
        if (profilePicUploaded && inputHasErrors && inputPasswordEquals)
        {
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("email");
            request.getSession().removeAttribute("oldpassword");
            request.getSession().removeAttribute("newpassword");
            request.getSession().removeAttribute("confirmpassword");
            System.out.println("Profile Pic");
            DaoFactory.getProfilePicDao().delete(userid);
            DaoFactory.getProfilePicDao().insert(userid,fileHandler);
            response.sendRedirect("/profile");
            return;
        }

        // checks profile pic uploaded, passwords empty username and email given and checked
        if (pUploadPSEmptyUCheckECheck) {
            User newUser = new User (
                    userid,
                    username,
                    email,
                    user.getPassword()
            );
            System.out.println(1);
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("email");
            request.getSession().removeAttribute("oldpassword");
            request.getSession().removeAttribute("newpassword");
            request.getSession().removeAttribute("confirmpassword");
            DaoFactory.getUsersDao().update(newUser);
            DaoFactory.getProfilePicDao().delete(userid);
            DaoFactory.getProfilePicDao().insert(userid,fileHandler);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/profile");
            return;
        }

        // checks profile pic not uploaded, passwords empty, username and email given and checked
        if (psEmptyUCheckECheck) {
            User newUser = new User (
                    userid,
                    username,
                    email,
                    user.getPassword()
            );
            System.out.println(2);
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("email");
            request.getSession().removeAttribute("oldpassword");
            request.getSession().removeAttribute("newpassword");
            request.getSession().removeAttribute("confirmpassword");
            DaoFactory.getUsersDao().update(newUser);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/profile");
            return;
        }

        // all fields given and checked
        if (vPasswordNCCheckAllFieldsCheckPNone && profilePicUploaded){
            User newUser = new User (
                    userid,
                    username,
                    email,
                    Password.hash(newpassword)
            );
            System.out.println(3);
            DaoFactory.getUsersDao().update(newUser);
            DaoFactory.getProfilePicDao().delete(userid);
            DaoFactory.getProfilePicDao().insert(userid,fileHandler);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/profile");
            return;
        }

        // old password and current password match, new password and confirm match, all fields given no profile pic uploaded
        if (vPasswordNCCheckAllFieldsCheckPNone){
            User newUser = new User (
                    userid,
                    username,
                    email,
                    Password.hash(newpassword)
            );
            System.out.println(4);
            DaoFactory.getUsersDao().update(newUser);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/profile");
            return;
        }

        // errors possible after all check
        if (inputsHasErrors || !inputPasswordEquals) {
            if (Validation.stringlength(username)) {
                request.setAttribute("error", "Username must be at least 5 characters");
            }else if (Validation.emailCheck(email)) {
                request.setAttribute("error", "Check email format");
            }else if(!usersEmail && !Validation.isExistingEmail(email)){
                request.setAttribute("error", "Email Taken");
            }else if (username.isEmpty()
                    || email.isEmpty()){
                request.setAttribute("error", "Username and Email minimum");
            }else if (Password.passlength(newpassword)
                    || Password.passlength(confirmpassword)) {
                request.setAttribute("error", "Password Length");
            }
            else if (!newpassword.equals(confirmpassword)){
                request.setAttribute("error", "Passwords dont match");
            } else {
                request.setAttribute("error", "Please check all fields");
            }
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("oldpassword", oldpassword);
            request.getSession().setAttribute("newpassword", newpassword);
            request.getSession().setAttribute("confirmpassword", confirmpassword);
            request.getRequestDispatcher("/WEB-INF/editProfile.jsp").forward(request, response);
        }
    }
  }
