package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "validity_period")
@NamedQuery(name = "ValidityPeriod.findAll", query = "SELECT p " +
                                                       "FROM ValidityPeriod p " +
                                                   "ORDER BY p.months ASC")
public class ValidityPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int months;

    @Column(name = "monthly_fee")
    private float monthlyFee;

    public int getId() {
        return id;
    }

    public int getMonths() {
        return months;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }
}
