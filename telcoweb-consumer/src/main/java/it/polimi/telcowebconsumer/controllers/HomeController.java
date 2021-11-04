package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.ServicePackage;
import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.services.ServicePackageService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @EJB
    private ServicePackageService servicePackageService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        req.setAttribute("username", user != null ? user.getUsername() : "");

        List<ServicePackage> servicePackageList = servicePackageService.getAllServicePackages();

        req.setAttribute("packages", servicePackageList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
}
