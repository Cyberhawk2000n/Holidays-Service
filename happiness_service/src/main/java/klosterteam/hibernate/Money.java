/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Money", uniqueConstraints = {
        @UniqueConstraint(columnNames = "hist_id")})
public class Money implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hist_id", foreignKey = @ForeignKey(name = "FK_Money_hist_id_Events_id"),
            unique = true, nullable = false)
    private Events histId;
    @Column(name="money", unique = false, nullable = false)
    private long money;
    @Column(name="money_max", unique = false, nullable = false)
    private long moneyMax;

    public Money() {
    }

    public Events getHistId() {
        return histId;
    }

    public void setHistId(Events histId) {
        this.histId = histId;
    }


    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getMoneyMax() {
        return moneyMax;
    }

    public void setMoneyMax(long moneyMax) {
        this.moneyMax = moneyMax;
    }

    public Money(Events histId, long money, long moneyMax) {
        this.histId = histId;
        this.money = money;
        this.moneyMax = moneyMax;
    }
    
}
