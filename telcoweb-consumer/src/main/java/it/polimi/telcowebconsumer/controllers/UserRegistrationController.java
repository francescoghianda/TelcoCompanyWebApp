package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.exception.UserAlreadyExistsException;
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
import java.util.Set;

@WebServlet(value = "/signup")
public class UserRegistrationController extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean autoLogin = Boolean.parseBoolean(req.getParameter("autoLogin"));

        if(email == null || email.isEmpty() ||
            username == null || username.isEmpty() ||
            password == null || password.isEmpty()){

            resp.sendError(400, "Some fields are missing!");
            return;
        }

        try {
            userService.register(username, email, password, Set.of(UserRole.CUSTOMER));

            if(autoLogin){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/signin");
                dispatcher.forward(req, resp);
                return;
            }

            resp.sendRedirect(getServletContext().getContextPath()+"/?m="+Message.REG_OK);

        } catch (UserAlreadyExistsException e) {
            //resp.sendError(409, "User already exists!");
            resp.sendRedirect(getServletContext().getContextPath()+"/?m="+ Message.REG_FAIL);
        }
    }
}
