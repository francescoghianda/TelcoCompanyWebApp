package it.polimi.telcoejb.externalServices;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.services.ActivationScheduleService;
import it.polimi.telcoejb.services.OrderService;
import it.polimi.telcoejb.services.PaymentAlertService;
import it.polimi.telcoejb.services.UserService;
import it.polimi.telcoejb.utils.OrderStatus;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.Random;
import java.util.Set;

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
                Set<Order> rejectedOrders = orderService.findRejectedOrder(order.getOwner().getUsername());
                if(rejectedOrders.isEmpty()) order.getOwner().setInsolvent(false);
                userService.save(order.getOwner());
            }

            order.setStatus(OrderStatus.VALID);
            activationScheduleService.create(order);
        }
        else{
            order.setStatus(OrderStatus.REJECTED);
            order.getOwner().setInsolvent(true);
            int failedPayments = order.getOwner().incrementAndGetFailedPayments();
            if(failedPayments >= 3) alertService.createOrUpdateAlert(order);
            userService.save(order.getOwner());
        }

        orderService.save(order);

        return successfulPayment;
    }

    private boolean callExternalPaymentService() {
        return new Random().nextBoolean();
    }
}
