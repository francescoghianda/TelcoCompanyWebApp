package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "ServicePackage.findAll", query = "SELECT s FROM ServicePackage s")
@Table(name = "service_package")
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /*@OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private Set<FixedPhoneService> fixedPhoneServices;*/

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private Set<PhoneService> phoneServices;

    /*@OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private Set<FixedInternetService> fixedInternetServices;*/

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private Set<InternetService> internetServices;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_optional_product",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="optional_product_id", referencedColumnName="id")
    )
    private Set<OptionalProduct> optionalProducts;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_validity_period",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="validity_period_id", referencedColumnName="id")
    )
    private Set<ValidityPeriod> validityPeriods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Set<FixedPhoneService> getFixedPhoneServices() {
        return fixedPhoneServices;
    }*/

    public Set<PhoneService> getPhoneServices() {
        return phoneServices;
    }

    /*public Set<FixedInternetService> getFixedInternetServices() {
        return fixedInternetServices;
    }*/

    public Set<InternetService> getInternetServices() {
        return internetServices;
    }

    public Set<OptionalProduct> getOptionalProducts(){
        return optionalProducts;
    }

    public Set<ValidityPeriod> getValidityPeriods(){
        return validityPeriods;
    }

    /*public void addFixedPhoneService(FixedPhoneService service){
        fixedPhoneServices.add(service);
    }*/

    public void addPhoneService(PhoneService service){
        phoneServices.add(service);
    }

    /*public void addFixedInternetService(FixedInternetService service){
        fixedInternetServices.add(service);
    }*/

    public void addInternetService(InternetService service){
        internetServices.add(service);
    }

    public void addOptionalProduct(OptionalProduct optionalProduct){
        optionalProducts.add(optionalProduct);
    }

    public void addValidityPeriod(ValidityPeriod validityPeriod){
        validityPeriods.add(validityPeriod);
    }

    public Set<Service> getAllService(){
        Set<Service> services = new HashSet<>();
        //services.addAll(fixedPhoneServices);
        services.addAll(phoneServices);
        services.addAll(internetServices);
        //services.addAll(fixedInternetServices);
        return services;
    }
}
