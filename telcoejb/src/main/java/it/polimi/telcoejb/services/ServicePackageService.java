package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.*;
import it.polimi.telcoejb.exception.OptionalProductNotFoundException;
import it.polimi.telcoejb.exception.ServiceNotFoundException;
import it.polimi.telcoejb.exception.ValidityPeriodNotFoundException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ServicePackageService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    @EJB
    private ServiceService serviceService;

    @EJB
    private ValidityPeriodService validityPeriodService;

    @EJB
    private OptionalProductService optionalProductService;

    public List<ServicePackage> getAllServicePackages(){
        return em.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList();
    }

    public ServicePackage findById(int servicePackageId){
        return em.find(ServicePackage.class, servicePackageId);
    }

    public void createServicePackage(String name, List<Integer> serviceIds, List<Integer> optionalProductIds, List<Integer> validityPeriodIds) throws ServiceNotFoundException, OptionalProductNotFoundException, ValidityPeriodNotFoundException {
        List<Service> services = serviceService.findByIds(serviceIds);
        if(serviceIds.size() != services.size()) throw new ServiceNotFoundException();

        List<InternetService> internetServices = services.stream()
                .filter(service -> service instanceof InternetService)
                .map(service -> (InternetService) service)
                .collect(Collectors.toList());

        List<PhoneService> phoneServices = services.stream()
                .filter(service -> service instanceof PhoneService)
                .map(service -> (PhoneService) service)
                .collect(Collectors.toList());


        List<ValidityPeriod> validityPeriods = validityPeriodService.findByIds(validityPeriodIds);
        if(validityPeriodIds.size() != validityPeriods.size()) throw new ValidityPeriodNotFoundException();

        List<OptionalProduct> optionalProducts = optionalProductService.findByIds(optionalProductIds);
        if(optionalProductIds.size() != optionalProducts.size()) throw new OptionalProductNotFoundException();

        ServicePackage servicePackage = new ServicePackage();
        em.persist(servicePackage);

        servicePackage.setName(name);
        servicePackage.setInternetServices(internetServices);
        servicePackage.setPhoneServices(phoneServices);
        servicePackage.setOptionalProducts(optionalProducts);
        servicePackage.setValidityPeriods(validityPeriods);
    }
}
