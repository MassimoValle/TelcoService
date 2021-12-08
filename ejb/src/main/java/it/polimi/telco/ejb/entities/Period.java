package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Period.getAll",
                query = "SELECT p FROM Period p")
})

@Entity
public class Period {
    @Id
    @Column(name = "Month", nullable = false)
    private Integer id;

    @Column(name = "MonthlyFee", nullable = false)
    private Integer monthlyFee;

    public Integer getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Integer monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}