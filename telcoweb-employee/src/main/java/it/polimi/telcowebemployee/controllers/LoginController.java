package it.polimi.telcowebemployee.controllers;

import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.exception.UserNotFoundException;
import it.polimi.telcoejb.services.UserService;
import it.polimi.telcoejb.utils.UserRole;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @EJB
    private UserService userService;

    private static final int ERROR_USER_NOT_FOUND = 0;
    private static final int ERROR_WRONG_CREDENTIAL = 1;
    private static final int ERROR_FORBIDDEN = 2;


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int error = Integer.parseInt(Objects.requireNonNullElse(req.getParameter("error"), "-1"));
        String redirect = Objects.requireNonNullElse(req.getParameter("redirect"), "");

        req.setAttribute("error", error);
        req.setAttribute("redirect", redirect);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String redirect = req.getParameter("redirect");

        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            resp.sendError(400, "Username and/or password are missing!");
            return;
        }

        try {
            User user = userService.login(username, password);

            if(user == null){
                resp.sendRedirect(getErrorRedirectPath(req, ERROR_WRONG_CREDENTIAL));
                return;
            }

            if(!user.hasRole(UserRole.EMPLOYEE)){
                resp.sendRedirect(getErrorRedirectPath(req, ERROR_FORBIDDEN));
                return;
            }

            req.getSession().setAttribute("username", user.getUsername());

            resp.sendRedirect(req.getContextPath() + Objects.requireNonNullElse(redirect, "/"));

        } catch (UserNotFoundException e) {
            resp.sendRedirect(getErrorRedirectPath(req, ERROR_USER_NOT_FOUND));
        }
        catch (Exception e){
            resp.sendError(500);
        }
    }

    private String getErrorRedirectPath(HttpServletRequest req, int error){
        String redirect = req.getParameter("redirect");
        String path = req.getContextPath()+"/login?error="+error;
        if(redirect != null) path += "&redirect="+redirect;
        return path;
    }
}
