package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.entities.ServicePackage;
import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.services.OrderService;
import it.polimi.telcoejb.services.ServicePackageService;
import it.polimi.telcoejb.services.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @EJB
    private ServicePackageService servicePackageService;

    @EJB
    private OrderService orderService;

    @EJB
    private UserService userService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = (String) req.getSession().getAttribute("username");

        if(username != null){
            User user = userService.findByUsername(username);
            Set<Order> rejectedOrders = orderService.findRejectedOrder(username);
            req.setAttribute("rejectedOrders", rejectedOrders);
            req.setAttribute("user", user);
        }

        Set<ServicePackage> servicePackageList = servicePackageService.getAllServicePackages();

        req.setAttribute("packages", servicePackageList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
}
