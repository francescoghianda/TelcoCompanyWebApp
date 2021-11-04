package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.exception.UserAlreadyExistsException;
import it.polimi.telcoejb.services.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/signup")
public class UserRegistrationController extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(email == null || email.isEmpty() ||
            username == null || username.isEmpty() ||
            password == null || password.isEmpty()){

            resp.sendError(400, "Some fields are missing!");
            return;
        }

        try {
            userService.register(username, email, password);
            resp.sendRedirect(getServletContext().getContextPath()+"/");

        } catch (UserAlreadyExistsException e) {
            resp.sendError(409, "User already exists!");
        }
    }
}
