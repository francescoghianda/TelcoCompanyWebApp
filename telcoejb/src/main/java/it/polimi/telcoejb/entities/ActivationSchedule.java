package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table(name = "activation_schedule")
@Entity
public class ActivationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "activation_date", nullable = false)
    private LocalDate activationDate;

    @Column(name = "deactivation_date", nullable = false)
    private LocalDate deactivationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "activation_schedule_services",
            joinColumns = @JoinColumn(name = "activation_schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> services;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "activation_schedule_optional_products",
            joinColumns = @JoinColumn(name = "activation_schedule_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "optional_product_id", referencedColumnName = "id"))
    private List<OptionalProduct> optionalProducts;

    public LocalDate getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(LocalDate deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
        this.optionalProducts = optionalProducts;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}