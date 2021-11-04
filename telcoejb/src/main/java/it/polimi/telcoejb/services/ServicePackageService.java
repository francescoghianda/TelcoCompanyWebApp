package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.ServicePackage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ServicePackageService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public List<ServicePackage> getAllServicePackages(){
        return em.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList();
    }


}
