package it.polimi.telcowebemployee.controllers;

import it.polimi.telcoejb.entities.OptionalProduct;
import it.polimi.telcoejb.entities.Service;
import it.polimi.telcoejb.entities.ValidityPeriod;
import it.polimi.telcoejb.services.OptionalProductService;
import it.polimi.telcoejb.services.ServiceService;
import it.polimi.telcoejb.services.ValidityPeriodService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", value = "")
public class HomeController extends HttpServlet {

    @EJB
    private OptionalProductService optionalProductService;

    @EJB
    private ValidityPeriodService validityPeriodService;

    @EJB
    private ServiceService serviceService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("username", username);

        List<Service> services = serviceService.getAll();
        List<OptionalProduct> optionalProducts = optionalProductService.getAll();
        List<ValidityPeriod> validityPeriods = validityPeriodService.getAll();

        req.setAttribute("services", services);
        req.setAttribute("optionalProducts", optionalProducts);
        req.setAttribute("validityPeriods", validityPeriods);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
