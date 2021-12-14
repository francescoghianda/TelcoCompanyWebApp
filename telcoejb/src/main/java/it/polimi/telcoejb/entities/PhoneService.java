package it.polimi.telcoejb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "phone_service")
public class PhoneService extends Service{

    private int minutes;

    private int sms;

    @Column(name = "extra_minutes_fee")
    private float extraMinutesFee;

    @Column(name = "extra_sms_fee")
    private float extraSmsFee;

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public float getExtraMinutesFee() {
        return extraMinutesFee;
    }

    public void setExtraMinutesFee(float extraMinutesFee) {
        this.extraMinutesFee = extraMinutesFee;
    }

    public float getExtraSmsFee() {
        return extraSmsFee;
    }

    public void setExtraSmsFee(float extraSmsFee) {
        this.extraSmsFee = extraSmsFee;
    }
}
