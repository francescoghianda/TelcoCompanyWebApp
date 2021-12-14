package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.ServicePackage;
import it.polimi.telcoejb.entities.ValidityPeriod;
import it.polimi.telcoejb.services.ServicePackageService;
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
import java.util.Objects;
import java.util.Set;

@WebServlet("/buy-service")
public class BuyServiceController extends HttpServlet {

    @EJB
    private ServicePackageService servicePackageService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute("order") != null){
            resp.sendRedirect(getServletContext().getContextPath()+"/confirmation");
            return;
        }

        Set<ServicePackage> servicePackageList = servicePackageService.getAllServicePackages();
        req.setAttribute("packages", servicePackageList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/buy-service-1.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if(req.getSession().getAttribute("order") != null){
            resp.sendRedirect(getServletContext().getContextPath()+"/confirmation");
            return;
        }

        int servicePackageId = Integer.parseInt(Objects.requireNonNullElse(req.getParameter("service-package"), "-1"));

        ServicePackage servicePackage = servicePackageService.findById(servicePackageId);
        req.setAttribute("servicePackage", servicePackage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/buy-service-2.jsp");
        dispatcher.forward(req, resp);
    }
}
