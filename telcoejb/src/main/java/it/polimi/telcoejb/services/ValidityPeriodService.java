package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.ValidityPeriod;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ValidityPeriodService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public List<ValidityPeriod> getAll(){
        return em.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class).getResultList();
    }

    public ValidityPeriod findById(int validityPeriodId){
        return em.find(ValidityPeriod.class, validityPeriodId);
    }
}
