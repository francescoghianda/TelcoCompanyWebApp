package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "ServicePackage.findAll", query = "SELECT s FROM ServicePackage s")
@Table(name = "service_package")
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
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
    private List<PhoneService> phoneServices;

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
    private List<InternetService> internetServices;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_optional_product",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="optional_product_id", referencedColumnName="id")
    )
    private List<OptionalProduct> optionalProducts;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_validity_period",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="validity_period_id", referencedColumnName="id")
    )
    private List<ValidityPeriod> validityPeriods;

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

    public List<PhoneService> getPhoneServices() {
        return phoneServices;
    }

    public List<InternetService> getInternetServices() {
        return internetServices;
    }

    public List<OptionalProduct> getOptionalProducts(){
        return optionalProducts;
    }

    public List<ValidityPeriod> getValidityPeriods(){
        return validityPeriods;
    }

    public void setInternetServices(List<InternetService> internetServices){
        this.internetServices = internetServices;
    }

    public void setPhoneServices(List<PhoneService> phoneServices){
        this.phoneServices = phoneServices;
    }

    public void setOptionalProducts(List<OptionalProduct> optionalProducts){
        this.optionalProducts = optionalProducts;
    }

    public void setValidityPeriods(List<ValidityPeriod> validityPeriods){
        this.validityPeriods = validityPeriods;
    }
    /*public void setPhoneServices(List<PhoneService> phoneServices){
        this.phoneServices = phoneServices;
    }

    public void addInternetServices(List<InternetService> internetServices){
        this.internetServices = internetServices;
    }

    public void setOptionalProducts(List<OptionalProduct> optionalProducts){
        this.optionalProducts = optionalProducts;
    }

    public void setValidityPeriods(List<ValidityPeriod> validityPeriods){
        this.validityPeriods = validityPeriods;
    }*/

    public List<Service> getAllService(){
        List<Service> services = new ArrayList<>();
        services.addAll(phoneServices);
        services.addAll(internetServices);
        return services;
    }
}
