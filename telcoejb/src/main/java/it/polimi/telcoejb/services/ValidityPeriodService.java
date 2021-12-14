package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.ValidityPeriod;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Set;

@Stateless
public class ValidityPeriodService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public Set<ValidityPeriod> getAllValidityPeriods(){
        return new HashSet<>(em.createQuery("SELECT vp FROM ValidityPeriod vp", ValidityPeriod.class).getResultList());
    }

    public ValidityPeriod findById(int validityPeriodId){
        return em.find(ValidityPeriod.class, validityPeriodId);
    }
}
