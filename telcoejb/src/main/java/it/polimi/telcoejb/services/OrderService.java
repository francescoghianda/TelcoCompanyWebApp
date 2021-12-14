package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.*;
import it.polimi.telcoejb.exception.InvalidServicePackageCombination;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Stateless
public class OrderService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    @EJB
    private UserService userService;

    @EJB
    private OptionalProductService optionalProductService;

    @EJB
    private ServicePackageService servicePackageService;

    @EJB
    private ValidityPeriodService validityPeriodService;

    /**
     * Instantiate the entity of the order, but not call the persist method.<br>
     * After the confirmation of the order, is needed to call the persist method in order to store the order in the database.
     * @param servicePackageId The id of service package chose by the user
     * @param validityPeriodId The validity period chosen by the user
     * @param startDate The date on which the services will be active
     * @param optionalProductIds A list of optional products' ids chosen by the user
     * @return The entity of the order without the id and the user
     * @throws InvalidServicePackageCombination If the validity period or at least one optional product are not available for the chosen service package
     */
    public Order createOrder(int servicePackageId, int validityPeriodId, Date startDate, Set<Integer> optionalProductIds) throws InvalidServicePackageCombination {
        Order order = new Order();
        //em.persist(order);

        Timestamp creationDate = Timestamp.from(Instant.now());

        ServicePackage servicePackage = servicePackageService.findById(servicePackageId);
        ValidityPeriod validityPeriod = validityPeriodService.findById(validityPeriodId);
        Set<OptionalProduct> optionalProducts = optionalProductService.findByIds(optionalProductIds);

        //Check that the validity period and the optional products are available for the chosen service package
        if(!servicePackage.getValidityPeriods().contains(validityPeriod) ||
                !servicePackage.getOptionalProducts().containsAll(optionalProducts)){
            throw new InvalidServicePackageCombination();
        }

        order.setCreationDate(creationDate);
        order.setStartDate(startDate);
        order.setServicePackage(servicePackage);
        order.setValidityPeriod(validityPeriod);
        order.setOptionalProducts(optionalProducts);

        //(monthly fee of service package * number of months) + (sum of monthly fees of options * number of months)
        float servicePackagePrice = (validityPeriod.getMonthlyFee()*validityPeriod.getMonths());
        float optionalProductsPrice = (float) (optionalProducts.stream().mapToDouble(OptionalProduct::getMonthlyFee).sum() * validityPeriod.getMonths());
        float totalPrice = servicePackagePrice + optionalProductsPrice;
        order.setTotalPrice(totalPrice);

        return order;
    }

    public Set<Order> findRejectedOrder(String username){
        return new HashSet<>(em.createNamedQuery("Order.findRejectedOrder", Order.class).setParameter("username", username).getResultList());
    }

    public Optional<Order> findById(int orderId){
        return Optional.ofNullable(em.find(Order.class, orderId));
    }

    public void persist(Order order, String username){
        User user = userService.findByUsername(username);
        order.setOwner(user);
        em.persist(order);
    }

    public void save(Order order){
        em.merge(order);
    }
}
