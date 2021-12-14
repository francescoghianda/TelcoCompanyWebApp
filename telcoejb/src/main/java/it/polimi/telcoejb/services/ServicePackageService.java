package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.ServicePackage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Set;

@Stateless
public class ServicePackageService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public Set<ServicePackage> getAllServicePackages(){
        return new HashSet<>(em.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList());
    }

    public ServicePackage findById(int servicePackageId){
        return em.find(ServicePackage.class, servicePackageId);
    }

}
