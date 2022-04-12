package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.ServicePackage;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_avg_optional_product_per_package")
@NamedQuery(name = "AverageOptionalProductPerPackage.findAll", query = "select stat from AverageOptionalProductPerPackage stat order by stat.averageOptionalProductSalesPerPackage desc")
public class AverageOptionalProductPerPackage {

    @Id
    @Column(name = "service_package_id")
    private int servicePackageId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ServicePackage servicePackage;

    @Column(name = "tot_number_opt_prod_sales")
    private int totalOptionalProductSales;

    @Column(name = "avg_number_opt_prod_sales")
    private double averageOptionalProductSalesPerPackage;

    public double getAverageOptionalProductSalesPerPackage() {
        return averageOptionalProductSalesPerPackage;
    }

    public int getTotalOptionalProductSales() {
        return totalOptionalProductSales;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }
}