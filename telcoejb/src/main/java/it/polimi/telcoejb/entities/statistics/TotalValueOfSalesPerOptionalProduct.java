package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.OptionalProduct;
import jakarta.persistence.*;

@Entity
@Table(name = "stat_total_value_of_sales_per_optional_product")
@NamedQuery(name = "TotalValueOfSalesPerOptionalProduct.findBestSeller", query = "select stat from TotalValueOfSalesPerOptionalProduct stat order by stat.totalValueOfSales desc")
public class TotalValueOfSalesPerOptionalProduct {

    @Id
    @Column(name = "optional_product_id")
    private int optionalProductId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private OptionalProduct optionalProduct;

    @Column(name = "tot_value_of_sales")
    private float totalValueOfSales;

    public OptionalProduct getOptionalProduct() {
        return optionalProduct;
    }

    public float getTotalValueOfSales() {
        return totalValueOfSales;
    }
}
