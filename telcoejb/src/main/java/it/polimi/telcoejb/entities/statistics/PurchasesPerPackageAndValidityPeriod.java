package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.ServicePackage;
import it.polimi.telcoejb.entities.ValidityPeriod;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_purchases_per_package_and_validity_period")
@NamedQuery(name = "PurchasesPerPackageAndValidityPeriod.findAll", query = "select stat from PurchasesPerPackageAndValidityPeriod stat order by stat.servicePackage.name, stat.totalPurchases desc")
public class PurchasesPerPackageAndValidityPeriod {

    @EmbeddedId
    private PurchasesPerPackageAndValidityPeriodPK PK;

    @MapsId("servicePackageId")
    @ManyToOne
    @JoinColumn(name = "service_package_id")
    private ServicePackage servicePackage;

    @MapsId("validityPeriodId")
    @ManyToOne
    @JoinColumn(name = "validity_period_id")
    private ValidityPeriod validityPeriod;

    @Column(name = "number_of_purchases")
    private int totalPurchases;

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public ValidityPeriod getValidityPeriod(){
        return validityPeriod;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }
}
