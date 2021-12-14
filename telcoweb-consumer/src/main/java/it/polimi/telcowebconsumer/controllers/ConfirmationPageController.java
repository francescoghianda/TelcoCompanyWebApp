package it.polimi.telcowebconsumer.controllers;

import it.polimi.telcoejb.entities.Order;
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

@WebServlet("/confirmation")
public class ConfirmationPageController extends HttpServlet {

    @EJB
    private OrderService orderService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Order> order = getOrder(req);

        if(order.isEmpty() || order.get().getStatus().equals(OrderStatus.VALID)){
            resp.sendRedirect(getServletContext().getContextPath()+"/home");
            return;
        }

        req.setAttribute("order", order.get());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/confirmation.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = (String) req.getSession().getAttribute("username");
        Optional<Order> orderOpt = getOrder(req);

        if(orderOpt.isEmpty() || orderOpt.get().getStatus().equals(OrderStatus.VALID)){
            resp.sendRedirect(getServletContext().getContextPath()+"/home");
            return;
        }

        Order order = orderOpt.get();

        if(username == null){
            resp.sendRedirect(getServletContext().getContextPath()+"/?redirect=/confirmation?order="+order.getId());
            return;
        }

        req.getSession().removeAttribute("order");
        if(order.getOwner() == null) orderService.persist(order, username);

        resp.sendRedirect(getServletContext().getContextPath()+"/execute-payment?order="+order.getId());
    }

    private Optional<Order> getOrder(HttpServletRequest req){
        int orderId = Integer.parseInt(Objects.requireNonNullElse(req.getParameter("order"), "0"));
        if(orderId == 0) return Optional.ofNullable((Order) req.getSession().getAttribute("order"));
        else return orderService.findById(orderId);
    }
}
