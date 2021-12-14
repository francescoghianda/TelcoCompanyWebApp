package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcowebconsumer.utils.Message;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "index", urlPatterns = {"/index"})
public class IndexController extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String redirect = req.getParameter("redirect");

        Message.fromRequest(req).ifPresent(message -> req.setAttribute("message", message));

        req.setAttribute("onlyLogin", redirect != null);
        req.setAttribute("redirect", redirect == null ? "/home" : redirect);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index-page.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
