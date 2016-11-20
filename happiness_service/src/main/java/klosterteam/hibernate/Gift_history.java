/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Cyberhawk
 */
@Table(name = "Gift_history")
public class Gift_history implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_id", foreignKey = @ForeignKey(name = "FK_Gift_history_gift_id_Gifts_id"), unique = false, nullable = false)
    private Gifts giftId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hist_id", foreignKey = @ForeignKey(name = "FK_Gift_history_hist_id_History_id"), unique = true, nullable = false)
    private History histId;

    public Gift_history() {
    }

    public Gifts getGiftId() {
        return giftId;
    }

    public void setGiftId(Gifts giftId) {
        this.giftId = giftId;
    }

    public History getHistId() {
        return histId;
    }

    public void setHistId(History histId) {
        this.histId = histId;
    }
    
}
