package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.Order;
import it.polimi.telcoejb.entities.User;
import it.polimi.telcoejb.entities.statistics.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StatisticsService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public List<PurchasesPerPackage> getPurchasesPerPackage(){
        return em.createNamedQuery("PurchasesPerPackage.findAll", PurchasesPerPackage.class).getResultList();
    }

    public List<PurchasesPerPackageAndValidityPeriod> getPurchasesPerPackageAndValidityPeriod(){
        return em.createNamedQuery("PurchasesPerPackageAndValidityPeriod.findAll", PurchasesPerPackageAndValidityPeriod.class).getResultList();
    }

    public List<TotalValueOfSalesPerPackage> getTotalValueOfSalesPerPackage(){
        return em.createNamedQuery("TotalValueOfSalesPerPackage.findAll", TotalValueOfSalesPerPackage.class).getResultList();
    }

    public List<AverageOptionalProductPerPackage> getAverageOptionalProductsPerPackage(){
        return em.createNamedQuery("AverageOptionalProductPerPackage.findAll", AverageOptionalProductPerPackage.class).getResultList();
    }

    public TotalValueOfSalesPerOptionalProduct getBestSellerOptionalProduct(){
        return em.createNamedQuery("TotalValueOfSalesPerOptionalProduct.findBestSeller", TotalValueOfSalesPerOptionalProduct.class).setMaxResults(1).getSingleResult();
    }

    public List<User> getInsolventUsers(){
        return em.createNamedQuery("User.findInsolvent", User.class).getResultList();
    }

    public List<Order> getSuspendedOrder(int userId){
        return em.createNamedQuery("UserSuspendedOrder.findByUserId", UserSuspendedOrder.class).setParameter("userId", userId).getResultList()
                .stream().map(UserSuspendedOrder::getOrder).collect(Collectors.toList());
    }

}
