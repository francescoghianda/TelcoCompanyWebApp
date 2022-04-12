package it.polimi.telcowebemployee.controllers;

import it.polimi.telcoejb.entities.*;
import it.polimi.telcoejb.exception.OptionalProductNotFoundException;
import it.polimi.telcoejb.exception.ServiceNotFoundException;
import it.polimi.telcoejb.exception.ValidityPeriodNotFoundException;
import it.polimi.telcoejb.services.ServicePackageService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "NewServicePackageController", value = "/new-service-package")
public class NewServicePackageController extends HttpServlet {

    @EJB
    private ServicePackageService service;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String[] serviceIdsParam = req.getParameterValues("services");
        String[] optionalProductIdsParam = req.getParameterValues("optional-products");
        String[] validityPeriodIdsParam = req.getParameterValues("validity-periods");

        if(name == null || serviceIdsParam == null || validityPeriodIdsParam == null){
            resp.sendError(400);
            return;
        }

        List<Integer> serviceIds = Arrays.stream(serviceIdsParam).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> validityPeriodIds = Arrays.stream(validityPeriodIdsParam).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> optionalProductsIds =  optionalProductIdsParam == null ? Collections.emptyList() : Arrays.stream(optionalProductIdsParam).map(Integer::parseInt).collect(Collectors.toList());

        try {
            service.createServicePackage(name, serviceIds, optionalProductsIds, validityPeriodIds);

            resp.sendRedirect(req.getContextPath()+"/");
        }
        catch (ServiceNotFoundException e) {
            resp.sendError(400, "Invalid service id");
        }
        catch (OptionalProductNotFoundException e) {
            resp.sendError(400, "Invalid optional product id");
        }
        catch (ValidityPeriodNotFoundException e) {
            resp.sendError(400, "Invalid validity period id");
        }
    }
}
