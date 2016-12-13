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
@Table(name = "Departments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")}) 
public class Departments implements Serializable {
    @Id
    @Column(name="id", unique = true, nullable = false)
    private int id;
    @Column(name="name", unique = true, nullable = false, length = 64)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "FK_Departments_manager_id_Users_id"),
            unique = false, nullable = true)
    private Users managerId;

    public Departments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getManagerId() {
        return managerId;
    }

    public void setManagerId(Users managerId) {
        this.managerId = managerId;
    }

    public Departments(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public Departments(short id, String name, Users managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }
    
}