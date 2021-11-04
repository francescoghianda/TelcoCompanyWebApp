package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "ServicePackage.findAll", query = "SELECT s FROM ServicePackage s")
@Table(name = "service_package")
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private List<FixedPhoneService> fixedPhoneServices;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private List<MobilePhoneService> mobilePhoneServices;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private List<FixedInternetService> fixedInternetServices;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_service",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="service_id", referencedColumnName="id")
    )
    private List<MobileInternetService> mobileInternetServices;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "service_package_optional_product",
            joinColumns = @JoinColumn(name="service_package_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="optional_product_id", referencedColumnName="id")
    )
    private List<OptionalProduct> optionalProducts;

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

    public List<FixedPhoneService> getFixedPhoneServices() {
        return fixedPhoneServices;
    }

    public List<MobilePhoneService> getMobilePhoneServices() {
        return mobilePhoneServices;
    }

    public List<FixedInternetService> getFixedInternetServices() {
        return fixedInternetServices;
    }

    public List<MobileInternetService> getMobileInternetServices() {
        return mobileInternetServices;
    }

    public List<OptionalProduct> getOptionalProducts(){
        return optionalProducts;
    }

    public void addFixedPhoneService(FixedPhoneService service){
        fixedPhoneServices.add(service);
    }

    public void addMobilePhoneService(MobilePhoneService service){
        mobilePhoneServices.add(service);
    }

    public void addFixedInternetService(FixedInternetService service){
        fixedInternetServices.add(service);
    }

    public void addMobileInternetService(MobileInternetService service){
        mobileInternetServices.add(service);
    }

    public void addOptionalProduct(OptionalProduct optionalProduct){
        optionalProducts.add(optionalProduct);
    }
}
