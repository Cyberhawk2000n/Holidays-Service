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
@Table(name = "Gifts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Gifts implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @Column(name="name", unique = false, nullable = false, length = 32)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cat_id", foreignKey = @ForeignKey(name = "FK_Gifts_cat_id_Categories_id"),
            unique = false, nullable = false)
    private Categories catId;

    public Gifts() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCatId() {
        return catId;
    }

    public void setCatId(Categories catId) {
        this.catId = catId;
    }

    public Gifts(String name, Categories catId) {
        this.name = name;
        this.catId = catId;
    }
    
}
