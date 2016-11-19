/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

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
@Table(name = "Vote")
public class Vote implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hist_id", foreignKey = @ForeignKey(name = "FK_Vote_hist_id_History_id"), unique = false, nullable = false)
    private History histId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_id", foreignKey = @ForeignKey(name = "FK_Vote_gift_id_Gifts_id"), unique = false, nullable = false)
    private Gifts giftId;
    @Column(name="count", unique = false, nullable = false)
    private int count;

    public Vote() {
    }

    public History getHistId() {
        return histId;
    }

    public void setHistId(History histId) {
        this.histId = histId;
    }

    public Gifts getGiftId() {
        return giftId;
    }

    public void setGiftId(Gifts giftId) {
        this.giftId = giftId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
