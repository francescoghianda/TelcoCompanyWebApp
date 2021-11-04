package it.polimi.telcoejb.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "validity_period")
public class ValidityPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int months;

    private float monthlyFee;
}
