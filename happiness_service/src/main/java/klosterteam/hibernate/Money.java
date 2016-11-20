/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Cyberhawk
 */
@Table(name = "Money")
public class Money implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hist_id", foreignKey = @ForeignKey(name = "FK_Money_hist_id_History_id"), unique = true, nullable = false)
    private History histId;
    @Column(name="money", unique = false, nullable = false)
    private long money;
    @Column(name="money_max", unique = false, nullable = false)
    private long moneyMax;

    public Money() {
    }

    public History getHistId() {
        return histId;
    }

    public void setHistId(History histId) {
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
    
}
