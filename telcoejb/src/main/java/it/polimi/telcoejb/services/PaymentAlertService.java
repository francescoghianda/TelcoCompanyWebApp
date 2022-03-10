package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.entities.PaymentAlert;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.sql.Timestamp;
import java.time.Instant;

@Stateless
public class PaymentAlertService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    /**
     * Create or update an alert for the user that own the specified order.<br>
     * If an alert for the user already exist, the alert is updated with the current date and the total amount of the order,
     * otherwise, a new alert is generated for the user
     * @param order The order that was rejected
     */
    public void createOrUpdateAlert(Order order){

        PaymentAlert alert;
        try{
            alert = em.createNamedQuery("PaymentAlert.findByUserId", PaymentAlert.class)
                    .setParameter("userId", order.getOwner().getId())
                    .getSingleResult();

            alert.setAmount(order.getTotalPrice());
            alert.setCreationTime(Timestamp.from(Instant.now()));
            em.merge(alert);
        }
        catch (NoResultException e){
            alert = new PaymentAlert();
            em.persist(alert);

            alert.setUser(order.getOwner());
            alert.setAmount(order.getTotalPrice());
            alert.setCreationTime(Timestamp.from(Instant.now()));
        }
    }

    public void createAlert(Order order){
        PaymentAlert alert = new PaymentAlert();
        em.persist(alert);

        alert.setUser(order.getOwner());
        alert.setAmount(order.getTotalPrice());
        alert.setCreationTime(Timestamp.from(Instant.now()));
    }
}
