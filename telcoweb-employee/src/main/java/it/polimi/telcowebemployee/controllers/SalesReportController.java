package it.polimi.telcowebemployee.controllers;

import it.polimi.telcoejb.entities.PaymentAlert;
import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.services.PaymentAlertService;
import it.polimi.telcoejb.services.StatisticsService;
import it.polimi.telcowebemployee.utils.UserSuspendedOrdersAndAlerts;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SalesReportController", value = "/sales-report")
public class SalesReportController extends HttpServlet {

    @EJB
    private StatisticsService statisticsService;

    @EJB
    private PaymentAlertService paymentAlertService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> insolventUsers = statisticsService.getInsolventUsers();
        List<UserSuspendedOrdersAndAlerts> suspendedOrdersAndAlerts = new ArrayList<>();

        for(User user : insolventUsers){
            List<PaymentAlert> alerts = paymentAlertService.findByUserId(user.getId());
            suspendedOrdersAndAlerts.add(new UserSuspendedOrdersAndAlerts(user, statisticsService.getSuspendedOrder(user.getId()), alerts));


            //System.out.println(user.getUsername()+" -- "+user.getSuspendedOrders());
        }



        req.setAttribute("valueOfSalesPerPackage", statisticsService.getTotalValueOfSalesPerPackage());
        req.setAttribute("purchasesPerPackage", statisticsService.getPurchasesPerPackage());
        req.setAttribute("purchasesPerPackageAndValidityPeriod", statisticsService.getPurchasesPerPackageAndValidityPeriod());
        req.setAttribute("avgOptionalProductsPerPackage", statisticsService.getAverageOptionalProductsPerPackage());
        req.setAttribute("bestSellerOptionalProduct", statisticsService.getBestSellerOptionalProduct());
        req.setAttribute("suspendedOrdersAndAlerts", suspendedOrdersAndAlerts);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/sales-report.jsp");
        dispatcher.forward(req, resp);
    }
}
