package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.exception.UserNotFoundException;
import it.polimi.telcoejb.services.UserService;
import it.polimi.telcoejb.utils.UserRole;
import it.polimi.telcowebconsumer.utils.Message;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
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
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

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
                sendError(resp, Message.LOGIN_FAIL);
                return;
            }

            if(!user.hasRole(UserRole.CUSTOMER)){
                sendError(resp, Message.FORBIDDEN);
                return;
            }

            req.getSession().setAttribute("username", user.getUsername());

            String path = getServletContext().getContextPath() + (redirect != null ? redirect : "/home");
            resp.sendRedirect(path);

        } catch (UserNotFoundException e) {
            sendError(resp, Message.USER_NOT_FOUND);
        }
        catch (Exception e){
            resp.sendError(500);
        }
    }

    private void sendError(HttpServletResponse response, Message message) throws IOException {
        response.sendRedirect(getServletContext().getContextPath()+"/?m="+message.getCode());
    }

}
