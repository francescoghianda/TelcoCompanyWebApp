package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.utils.OrderStatus;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Random;

@Stateless
public class PaymentService {

    @EJB
    private OrderService orderService;

    @EJB
    private UserService userService;

    @EJB
    private ActivationScheduleService activationScheduleService;

    @EJB
    private PaymentAlertService alertService;

    public boolean executePayment(Order order){
        boolean successfulPayment = callExternalPaymentService();

        if(successfulPayment){

            if(order.getStatus().equals(OrderStatus.REJECTED)){
                List<Order> rejectedOrders = orderService.findRejectedOrder(order.getOwner().getUsername());
                if(rejectedOrders.size() == 1) order.getOwner().setInsolvent(false);
                //if(rejectedOrders.isEmpty()) L'ordine corrente è ancora REJECTED
                userService.save(order.getOwner());
            }

            order.setStatus(OrderStatus.VALID);
            activationScheduleService.create(order);
        }
        else{
            order.setStatus(OrderStatus.REJECTED);
            order.getOwner().setInsolvent(true);
            int failedPayments = order.getOwner().incrementAndGetFailedPayments();
            //if(failedPayments >= 3) alertService.createAlert(order);
            if(failedPayments % 3 == 0) alertService.createAlert(order);
            userService.save(order.getOwner());
        }

        orderService.save(order);

        return successfulPayment;
    }

    private boolean callExternalPaymentService() {
        return new Random().nextBoolean();
    }
}
