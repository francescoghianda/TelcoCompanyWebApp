package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.exception.InvalidServicePackageCombination;
import it.polimi.telcoejb.services.OrderService;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/create-subscription")
public class CreateSubscriptionController extends HttpServlet {

    @EJB
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int servicePackage = Integer.parseInt(req.getParameter("service-package"));
        int validityPeriod = Integer.parseInt(req.getParameter("validity-period"));
        Date startDate = Date.valueOf(req.getParameter("start-date"));
        String[] optionalProductsIds = req.getParameterValues("opt-product");

        List<Integer> optionalProductsIdList = new ArrayList<>();

        if(optionalProductsIds != null){
            for(String id : optionalProductsIds){
                optionalProductsIdList.add(Integer.parseInt(id));
            }
        }

        try {
            Order order = orderService.createOrder(servicePackage, validityPeriod, startDate, optionalProductsIdList);
            req.getSession().setAttribute("order", order);
            resp.sendRedirect(getServletContext().getContextPath()+"/confirmation");
        }
        catch (InvalidServicePackageCombination e) {
            resp.sendError(409, "Invalid service package");
        }
    }
}
