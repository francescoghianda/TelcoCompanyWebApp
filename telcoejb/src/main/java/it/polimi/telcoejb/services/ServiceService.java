package it.polimi.telcoejb.services;

import it.polimi.telcoejb.entities.InternetService;
import it.polimi.telcoejb.entities.PhoneService;
import it.polimi.telcoejb.entities.Service;
import it.polimi.telcoejb.utils.ServiceType;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Stateless
public class ServiceService {

    @PersistenceContext(unitName = "TelcoEJB")
    private EntityManager em;

    public void createPhoneService(ServiceType serviceType, int minutes, int sms, float minutesFee, float smsFee){
        if(serviceType != ServiceType.FIXED_PHONE && serviceType != ServiceType.MOBILE_PHONE) throw new IllegalArgumentException();

        PhoneService service = new PhoneService();
        em.persist(service);

        service.setServiceType(serviceType);
        service.setMinutes(minutes);
        service.setSms(sms);
        service.setExtraMinutesFee(minutesFee);
        service.setExtraSmsFee(smsFee);
    }

    public void createInternetService(ServiceType serviceType, int gb, float gbFee){
        if(serviceType != ServiceType.FIXED_INTERNET && serviceType != ServiceType.MOBILE_INTERNET) throw new IllegalArgumentException();

        InternetService service = new InternetService();
        em.persist(service);

        service.setServiceType(serviceType);
        service.setGigabytes(gb);
        service.setExtraGigabytesFee(gbFee);
    }

    public List<Service> getAll(){
        List<PhoneService> phoneServices = em.createNamedQuery("PhoneService.findAll", PhoneService.class).getResultList();
        List<InternetService> internetServices = em.createNamedQuery("InternetService.findAll", InternetService.class).getResultList();
        List<Service> services = new ArrayList<>(phoneServices);
        services.addAll(internetServices);
        return services;
    }
}
