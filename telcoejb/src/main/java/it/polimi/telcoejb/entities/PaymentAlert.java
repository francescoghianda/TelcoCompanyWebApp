package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@NamedQuery(name = "PaymentAlert.findByUserId", query = "SELECT a FROM PaymentAlert a WHERE a.user.id = :userId")
@Table(name = "payment_alert")
@Entity
public class PaymentAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumns(
            {@JoinColumn(name = "user_id", referencedColumnName = "id"),
            @JoinColumn(name = "username", referencedColumnName = "username"),
            @JoinColumn(name = "email", referencedColumnName = "email")}
    )
    private User user;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp dateTime) {
        this.creationTime = dateTime;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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
}