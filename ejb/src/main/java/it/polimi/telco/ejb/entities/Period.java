package it.polimi.telco.ejb.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = "Period.getAll",
                query = "SELECT p FROM Period p")
})

@Entity
public class Period {
    @Id
    @Column(name = "Month", nullable = false)
    private Integer id;

    @Column(name = "MonthlyFee", nullable = false, precision = 5, scale = 2)
    private BigDecimal monthlyFee;

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}