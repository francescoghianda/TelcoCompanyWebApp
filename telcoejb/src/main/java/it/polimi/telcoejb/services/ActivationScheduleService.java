package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.ActivationSchedule;
import it.polimi.telcoejb.entities.OptionalProduct;
import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.entities.Service;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class ActivationScheduleService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    /**
     * Create and persist the entity of the activation schedule for one order
     * @param order The order for which is needed to create the activation schedule
     */
    public void create(Order order){
        List<Service> services = order.getServicePackage().getAllService();
        List<OptionalProduct> optionalProducts = order.getOptionalProducts();
        LocalDate activationDate = order.getStartDate().toLocalDate();
        LocalDate deactivationDate = activationDate.plusMonths(order.getValidityPeriod().getMonths());

        ActivationSchedule activationSchedule = new ActivationSchedule();
        em.persist(activationSchedule);

        activationSchedule.setActivationDate(activationDate);
        activationSchedule.setDeactivationDate(deactivationDate);
        activationSchedule.setServices(services);
        activationSchedule.setOptionalProducts(optionalProducts);
        activationSchedule.setUser(order.getOwner());
    }
}
