package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.exception.UserNotFoundException;
import it.polimi.telcoejb.services.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login-servlet", value = "/signin")
public class LoginController extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            resp.sendError(400, "Username and/or password are missing!");
            return;
        }

        try {
            User user = userService.login(username, password);

            if(user == null){
                resp.sendError(401, "Wrong username or password!");
                return;
            }

            req.getSession().setAttribute("user", user);
            String path = getServletContext().getContextPath() + "/home";
            resp.sendRedirect(path);

        } catch (UserNotFoundException e) {
            resp.sendError(404, "User not found!");
        }
        catch (Exception e){
            resp.sendError(500);
        }
    }

}
