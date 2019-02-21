package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.admin;
import com.codeup.adlister.util.Password;
import com.codeup.adlister.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdminServlet", urlPatterns = "/createAdmin")
public class CreateAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        admin admin = (admin) request.getSession().getAttribute("admin");
        if (request.getSession().getAttribute("passcodeChecked") == null){
            request.getSession().setAttribute("passcodeChecked", 0);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        if (admin.getLevel().contains("3")){
            request.getRequestDispatcher("/WEB-INF/reports/createAdmin.jsp").forward(request, response);
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long newAdmin = Long.parseLong(request.getParameter("desireId"));
        long oldAdmin =Long.parseLong(request.getParameter("jediId"));
        String level = request.getParameter("level");
        String newAdminString = request.getParameter("desireId");
        String oldAdminString =request.getParameter("jediId");
        String passcode = request.getParameter("passcode");
        String passcodeConfirmation = request.getParameter("confirm_passcode");

        boolean inputHasErrors = newAdminString.isEmpty()
                || Password.passlength(passcode)
                || oldAdminString.isEmpty()
                || passcode.isEmpty()
                || !Validation.isExistingUserID(newAdmin)
                || Validation.isOldAdmin(newAdmin)
                || !Validation.isOldAdmin(oldAdmin)
                || (!passcode.equals(passcodeConfirmation));

        if (inputHasErrors) {
            if (newAdminString.isEmpty()
                    || oldAdminString.isEmpty()
                    || passcode.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
            }
            else if ((!passcode.equals(passcodeConfirmation))) {
                request.setAttribute("error", "Passwords Don't Match.");
            }
            else if (Validation.isOldAdmin(newAdmin)){
                request.setAttribute("error", "User is already Admin");
            }
            else if (Password.passlength(passcode)) {
                request.setAttribute("error", "Passcode must by at least 5 characters.");
            }
            else if (!Validation.isExistingUserID(newAdmin)) {
                request.setAttribute("error", "Id doesn't exist");
            }
            else if (Validation.isOldAdmin(oldAdmin)){
                request.setAttribute("error", "Admin doesnt exist");
            }
            else {
                request.setAttribute("error", "Please check information and try again");
            }
            request.getSession().setAttribute("desireId", newAdmin);
            request.getSession().setAttribute("jediId", oldAdmin);
            request.getRequestDispatcher("/WEB-INF/reports/createAdmin.jsp").forward(request, response);
            return;
        }else {
            admin admin = new admin(oldAdmin, newAdmin, level, passcode);
            DaoFactory.getAdminsDao().insert(admin);
            response.sendRedirect("/admin");
        }
    }
}
