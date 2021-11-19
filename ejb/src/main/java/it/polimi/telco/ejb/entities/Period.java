package it.polimi.telco.ejb.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Period.getAll",
                query = "SELECT p FROM Period p")
})

@Entity
public class Period {
    @Id
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Month", nullable = false)
    private Integer month;

    @Column(name = "MonthlyFee", nullable = false)
    private Integer monthlyFee;

    public Integer getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Integer monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }
}