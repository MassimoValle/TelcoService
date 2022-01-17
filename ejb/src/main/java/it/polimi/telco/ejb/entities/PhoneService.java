package it.polimi.telco.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class PhoneService{// extends Service{
    @Id
    @Column(name = "ServiceID", nullable = false)
    private Integer id;

    @Column(name = "DefaultMinutes")
    private Integer defaultMinutes;

    @Column(name = "DefaultSMS")
    private Integer defaultSMS;

    @Column(name = "ExtraMinutesFee", precision = 5, scale = 2)
    private BigDecimal extraMinutesFee;

    @Column(name = "ExtraSMSFee", precision = 5, scale = 2)
    private BigDecimal extraSMSFee;

    public BigDecimal getExtraSMSFee() {
        return extraSMSFee;
    }

    public void setExtraSMSFee(BigDecimal extraSMSFee) {
        this.extraSMSFee = extraSMSFee;
    }

    public BigDecimal getExtraMinutesFee() {
        return extraMinutesFee;
    }

    public void setExtraMinutesFee(BigDecimal extraMinutesFee) {
        this.extraMinutesFee = extraMinutesFee;
    }

    public Integer getDefaultSMS() {
        return defaultSMS;
    }

    public void setDefaultSMS(Integer defaultSMS) {
        this.defaultSMS = defaultSMS;
    }

    public Integer getDefaultMinutes() {
        return defaultMinutes;
    }

    public void setDefaultMinutes(Integer defaultMinutes) {
        this.defaultMinutes = defaultMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}