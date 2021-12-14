package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.OptionalProduct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class OptionalProductService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public Set<OptionalProduct> findByIds(Set<Integer> optionalProductIds){
        if(optionalProductIds.isEmpty()) return Collections.emptySet();
        return new HashSet<>(em.createNamedQuery("OptionalProduct.findByIds", OptionalProduct.class)
                .setParameter("ids", optionalProductIds)
                .getResultList());
    }
}
