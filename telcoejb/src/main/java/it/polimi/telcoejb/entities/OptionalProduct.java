package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

@NamedQuery(name = "OptionalProduct.findByIds", query = "SELECT o FROM OptionalProduct o WHERE o.id IN :ids")
@Table(name = "optional_product")
@Entity
public class OptionalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    @Column(name = "monthly_fee")
    private Float monthlyFee;

    public Float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}