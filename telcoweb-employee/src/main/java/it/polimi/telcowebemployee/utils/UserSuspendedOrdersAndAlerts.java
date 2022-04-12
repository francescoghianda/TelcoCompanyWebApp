package it.polimi.telcowebemployee.utils;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.entities.PaymentAlert;
import it.polimi.telcoejb.entities.User;

import java.util.List;

public class UserSuspendedOrdersAndAlerts {

    private final User user;

    private final List<Order> suspendedOrders;
    private final List<PaymentAlert> alerts;

    public UserSuspendedOrdersAndAlerts(User user, List<Order> suspendedOrders, List<PaymentAlert> alerts){
        this.user = user;
        this.suspendedOrders = suspendedOrders;
        this.alerts = alerts;
    }

    public User getUser() {
        return user;
    }

    public List<Order> getSuspendedOrders() {
        return suspendedOrders;
    }

    public List<PaymentAlert> getAlerts() {
        return alerts;
    }
}
