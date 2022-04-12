package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.services.PaymentService;
import it.polimi.telcoejb.services.OrderService;
import it.polimi.telcoejb.utils.OrderStatus;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/execute-payment")
public class PaymentController extends HttpServlet {

    @EJB
    private OrderService orderService;

    @EJB
    private PaymentService paymentService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int orderId;
        try{
            orderId = Integer.parseInt(req.getParameter("order"));
        }
        catch (NumberFormatException e){
            resp.sendError(400);
            return;
        }

        Optional<Order> order = orderService.findById(orderId);

        if(order.isEmpty()){
            resp.sendError(404, "Order not found!");
            return;
        }

        if(order.get().getStatus().equals(OrderStatus.VALID)){
            resp.sendError(409, "Order already payed");
            return;
        }

        boolean success = paymentService.executePayment(order.get());
        req.setAttribute("success", success);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/payment-summary.jsp");
        dispatcher.forward(req, resp);

    }
}
