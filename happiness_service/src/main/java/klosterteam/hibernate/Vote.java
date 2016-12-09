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
import javax.persistence.GeneratedValue;
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
@Table(name = "Vote", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Vote implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hist_id", unique = false, nullable = false)
    private Events histId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gift_id", foreignKey = @ForeignKey(name = "FK_Vote_gift_id_Gifts_id"), unique = false, nullable = false)
    private Gifts giftId;
    @Column(name="count", unique = false, nullable = false)
    private int count;

    public Vote() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Events getHistId() {
        return histId;
    }

    public void setHistId(Events histId) {
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

    public Vote(Events histId, Gifts giftId, int count) {
        this.histId = histId;
        this.giftId = giftId;
        this.count = count;
    }
    
}
