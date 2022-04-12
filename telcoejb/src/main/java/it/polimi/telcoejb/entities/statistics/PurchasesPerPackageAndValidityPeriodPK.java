package it.polimi.telcoejb.entities.statistics;

import it.polimi.telcoejb.entities.ServicePackage;
import it.polimi.telcoejb.entities.ValidityPeriod;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;

@Embeddable
public class PurchasesPerPackageAndValidityPeriodPK implements Serializable {

    @Column(name = "service_package_id")
    private int servicePackageId;

    @Column(name = "validity_period_id")
    private int validityPeriodId;

    @Override
    public boolean equals(Object o){
        if(!(o instanceof PurchasesPerPackageAndValidityPeriodPK)) return false;
        PurchasesPerPackageAndValidityPeriodPK pk = (PurchasesPerPackageAndValidityPeriodPK) o;
        return pk.servicePackageId == servicePackageId && pk.validityPeriodId == validityPeriodId;
    }

    @Override
    public int hashCode() {
        return 31 * servicePackageId + validityPeriodId;
    }

    /*@OneToOne
    @JoinColumn(name = "service_package_id")
    private ServicePackage servicePackage;

    @OneToOne
    @JoinColumn(name = "validity_period_id")
    private ValidityPeriod validityPeriod;

    public ServicePackage getServicePackage(){
        return servicePackage;
    }

    public ValidityPeriod getValidityPeriod(){
        return validityPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof PurchasesPerPackageAndValidityPeriodPK)) return false;
        PurchasesPerPackageAndValidityPeriodPK purchases = (PurchasesPerPackageAndValidityPeriodPK) o;
        return purchases.servicePackage.getId() == this.servicePackage.getId() && purchases.validityPeriod.getId() == this.validityPeriod.getId();
    }

    @Override
    public int hashCode() {
        int result = servicePackage != null ? servicePackage.hashCode() : 0;
        result = 31 * result + (validityPeriod != null ? validityPeriod.hashCode() : 0);
        return result;
    }*/
}
