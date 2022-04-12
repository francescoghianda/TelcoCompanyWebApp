package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.OptionalProduct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collections;
import java.util.List;

@Stateless
public class OptionalProductService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public List<OptionalProduct> findByIds(List<Integer> optionalProductIds){
        if(optionalProductIds.isEmpty()) return Collections.emptyList();
        return em.createNamedQuery("OptionalProduct.findByIds", OptionalProduct.class)
                .setParameter("ids", optionalProductIds)
                .getResultList();
    }

    public List<OptionalProduct> getAll(){
        return em.createNamedQuery("OptionalProduct.findAll", OptionalProduct.class).getResultList();
    }

    public void createOptionalProduct(String name, float monthlyFee){
        OptionalProduct optionalProduct = new OptionalProduct();
        em.persist(optionalProduct);

        optionalProduct.setName(name);
        optionalProduct.setMonthlyFee(monthlyFee);
    }
}
