package it.polimi.telco.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SalesReport2Id implements Serializable {
    private static final long serialVersionUID = 8335885266396804186L;
    @Column(name = "ServicePackage", nullable = false, length = 45)
    private String servicePackage;
    @Column(name = "Period", nullable = false)
    private Integer period;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicePackage, period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesReport2Id entity = (SalesReport2Id) o;
        return Objects.equals(this.servicePackage, entity.servicePackage) &&
                Objects.equals(this.period, entity.period);
    }
}