package it.polimi.telcowebemployee.controllers;

import it.polimi.telcoejb.services.OptionalProductService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "NewOptionalProductController", value = "/new-opt-product")
public class NewOptionalProductController extends HttpServlet {

    @EJB
    private OptionalProductService service;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try{
            String name = Objects.requireNonNull(req.getParameter("name"));
            float monthlyFee = Float.parseFloat(req.getParameter("monthly_fee"));
            service.createOptionalProduct(name, monthlyFee);
        }
        catch (NullPointerException | NumberFormatException e){
            resp.sendError(400);
        }

        resp.sendRedirect(req.getContextPath()+"/");

    }
}
