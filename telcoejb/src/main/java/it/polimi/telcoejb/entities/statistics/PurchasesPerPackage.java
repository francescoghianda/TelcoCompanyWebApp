package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.ServicePackage;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_purchases_per_package")
@NamedQuery(name = "PurchasesPerPackage.findAll", query = "select stat from PurchasesPerPackage stat order by stat.totalPurchases desc")
public class PurchasesPerPackage {

    @Id
    @Column(name = "service_package_id")
    private int servicePackageId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ServicePackage servicePackage;

    @Column(name = "number_of_purchases")
    private int totalPurchases;

    public ServicePackage getServicePackage(){
        return servicePackage;
    }

    public int getTotalPurchases(){
        return totalPurchases;
    }
}
