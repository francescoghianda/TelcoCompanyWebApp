package it.polimi.telcoejb.entities;

import it.polimi.telcoejb.utils.OrderStatus;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Order.findRejectedOrder", query = "SELECT ord FROM Order ord WHERE ord.status = it.polimi.telcoejb.utils.OrderStatus.REJECTED AND ord.owner.username = :username")
@Table(name = "`order`")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "start_date")
    private Date startDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_package", referencedColumnName = "id")
    private ServicePackage servicePackage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "validity_period", referencedColumnName = "id")
    private ValidityPeriod validityPeriod;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_optional_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "optional_product_id", referencedColumnName = "id"))
    private List<OptionalProduct> optionalProducts;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "`status`")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.INVALID;

    public List<OptionalProduct> getOptionalProducts() {
        return optionalProducts;
    }

    public void setOptionalProducts(List<OptionalProduct> optionalProducts){
        this.optionalProducts = optionalProducts;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status == null ? OrderStatus.INVALID : status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}