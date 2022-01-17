package it.polimi.telco.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class InternetService{// extends Service{
    @Id
    @Column(name = "ServiceID", nullable = false)
    private Integer id;

    @Column(name = "DefaultGB")
    private Integer defaultGB;

    @Column(name = "ExtraGBFee", precision = 5, scale = 2)
    private BigDecimal extraGBFee;

    public BigDecimal getExtraGBFee() {
        return extraGBFee;
    }

    public void setExtraGBFee(BigDecimal extraGBFee) {
        this.extraGBFee = extraGBFee;
    }

    public Integer getDefaultGB() {
        return defaultGB;
    }

    public void setDefaultGB(Integer defaultGB) {
        this.defaultGB = defaultGB;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}