package it.polimi.telcoejb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mobile_internet_service")
public class MobileInternetService extends Service {

    @Column(name = "gigabytes")
    private int gigabytes;

    @Column(name = "extra_gigabytes_fee")
    private float extraGigabytesFee;

    public int getGigabytes() {
        return gigabytes;
    }

    public void setGigabytes(int gigabytes) {
        this.gigabytes = gigabytes;
    }

    public float getExtraGigabytesFee() {
        return extraGigabytesFee;
    }

    public void setExtraGigabytesFee(float extraGigabytesFee) {
        this.extraGigabytesFee = extraGigabytesFee;
    }
}
