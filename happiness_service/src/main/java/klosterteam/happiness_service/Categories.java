/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Categories implements Serializable {
    @Id
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "FK_Categories_parent_id_Categories_id"), unique = false, nullable = false)
    private Categories parentId;
    @Column(name="name", unique = false, nullable = false, length = 64)
    private long name;

    public Categories() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Categories getParentId() {
        return parentId;
    }

    public void setParentId(Categories parentId) {
        this.parentId = parentId;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }
    
}
