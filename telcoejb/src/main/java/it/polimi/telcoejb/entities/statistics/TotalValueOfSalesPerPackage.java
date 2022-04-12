package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.ServicePackage;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_value_of_sales_per_package")
@NamedQuery(name = "TotalValueOfSalesPerPackage.findAll", query = "select stat from TotalValueOfSalesPerPackage stat order by stat.totalValueOfSales desc")
public class TotalValueOfSalesPerPackage {

    @Id
    @Column(name = "service_package_id")
    private int servicePackageId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ServicePackage servicePackage;

    @Column(name = "tot_value_of_sales")
    private float totalValueOfSales;

    @Column(name = "tot_value_of_sales_no_opt_products")
    private float totalValueOfSalesWithoutOptionalProducts;

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public float getTotalValueOfSales() {
        return totalValueOfSales;
    }

    public float getTotalValueOfSalesWithoutOptionalProducts() {
        return totalValueOfSalesWithoutOptionalProducts;
    }
}
