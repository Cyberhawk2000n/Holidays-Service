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
@Table(name = "Preferences", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Preferences implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private short id;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Preferences_user_id_Users_id"),
            unique = false, nullable = false)
    private Users userId;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cat_id", foreignKey = @ForeignKey(name = "FK_Preferences_cat_id_Categories_id"),
            unique = false, nullable = false)
    private Categories catId;

    public Preferences() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Categories getCatId() {
        return catId;
    }

    public void setCatId(Categories catId) {
        this.catId = catId;
    }

    public Preferences(Users userId, Categories catId) {
        this.userId = userId;
        this.catId = catId;
    }
    
}